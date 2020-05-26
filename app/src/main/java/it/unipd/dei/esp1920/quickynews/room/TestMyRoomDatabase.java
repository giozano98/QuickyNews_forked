package it.unipd.dei.esp1920.quickynews.room;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {News.class,RssNews.class},version = 1,exportSchema = false)
public abstract class TestMyRoomDatabase extends RoomDatabase
{
    //abstract NewsDao newsDao();
    abstract RssNewsDao rssNewsDao();

    private static volatile TestMyRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4; //TODO: capire quanti usarne effettivamente
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS); //singleton creation (singleton is useful
                                                                                                         // when exactly one object is needed)
    static TestMyRoomDatabase getDatabase(final Context context)
    {
        if(INSTANCE==null){
            synchronized (TestMyRoomDatabase.class){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                            TestMyRoomDatabase.class,"rssnews_database").addCallback(mRoomDataBaseCallBack).build();
                }
            }
        }

        return INSTANCE;

    }

    //Here we should put the code of the xml parser in order
    //to fulfill the screen with the news
    private static RoomDatabase.Callback mRoomDataBaseCallBack = new RoomDatabase.Callback(){
      @Override
      public void onOpen(@NonNull SupportSQLiteDatabase db){
          super.onOpen(db);
          Log.d("MyRoomDatabase","onOpen()");
          databaseWriteExecutor.execute(() -> {
              RssNewsDao rssDao = INSTANCE.rssNewsDao();
              RssNews rss = new RssNews(3,"www.prova.it","Provemo","me piaxe provare","22.05.20","provetta");
              rssDao.insertRssNews(rss);
          });
      }
    };


}