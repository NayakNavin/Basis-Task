package com.navinnayak.android.basistask.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.navinnayak.android.basistask.R;
import com.navinnayak.android.basistask.adapters.BasisDataAdapter;
import com.navinnayak.android.basistask.clients.BasisDataInterface;
import com.navinnayak.android.basistask.models.BasisData;
import com.navinnayak.android.basistask.models.BasisDataList;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.navinnayak.android.basistask.clients.BasisDataClient.getClient;


public class MainActivity extends AppCompatActivity implements CardStackListener {

    private BasisDataInterface basisDataInterface;
    private BasisDataAdapter basisDataAdapter;
    private CardStackView cardStackView;
    private FloatingActionButton fabPrevious, fabReset, fabNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        initializeBasisDataClient();
        setListeners();
    }

    /**
     * Initialization of FAB buttons and CardStackView
     */
    void initialize() {
        fabPrevious = findViewById(R.id.fab_previous);
        fabReset = findViewById(R.id.fab_reset);
        fabNext = findViewById(R.id.fab_next);
        cardStackView = findViewById(R.id.cardStack);
    }

    /**
     * Create OnClickListeners for the our FAB buttons
     */
    private void setListeners() {
        fabPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //When this button is clicked, previous is restored.
                cardStackView.rewind();
            }
        });

        fabReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //when this fab is clicked, the cards are reset to original position
                reset();
            }
        });


        fabNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //When this button is pressed, next card is shown.
                cardStackView.swipe();
            }
        });
    }


    /**
     * Custom method to reset the stacked cards
     */
    public void reset() {
        if (cardStackView.getLayoutManager() instanceof CardStackLayoutManager) {
            CardStackLayoutManager manager = (CardStackLayoutManager) cardStackView.getLayoutManager();
            cardStackView.smoothScrollToPosition(manager.getTopPosition() - manager.getTopPosition());
        }
    }


    /**
     * this method created an object of Retrofit instance and the calls Json and it enqueues callback.
     * Also displayed parsed json as stacked cards
     */
    void initializeBasisDataClient() {
        basisDataInterface = getClient().create(BasisDataInterface.class);
        Call<BasisDataList> call = basisDataInterface.getAllData();
        call.enqueue(new Callback<BasisDataList>() {

            @Override
            public void onResponse(Call<BasisDataList> call, Response<BasisDataList> response) {

                ArrayList<BasisData> datas = response.body().getData();
                basisDataAdapter = new BasisDataAdapter(datas);
                cardStackView.setLayoutManager(getCardStackLayoutManager());
                cardStackView.setAdapter(basisDataAdapter);
            }

            @Override
            public void onFailure(Call<BasisDataList> call, Throwable t) {

            }
        });
    }

    //custom card swipe settings
    private CardStackLayoutManager getCardStackLayoutManager() {
        CardStackLayoutManager mCardStackLayoutManager = new CardStackLayoutManager(getApplicationContext());
        mCardStackLayoutManager.setStackFrom(StackFrom.Top);
        mCardStackLayoutManager.setTranslationInterval(8f);
        mCardStackLayoutManager.setScaleInterval(0.85f);
        mCardStackLayoutManager.setMaxDegree(20.0f);
        mCardStackLayoutManager.setDirections(Direction.FREEDOM);
        mCardStackLayoutManager.setVisibleCount(4);
        return mCardStackLayoutManager;
    }

    /**
     * Empty methods of CardStackView Library.
     * It necessary to display these method.
     */
    @Override
    public void onCardDragging(Direction direction, float ratio) {

    }

    @Override
    public void onCardSwiped(Direction direction) {

    }

    @Override
    public void onCardRewound() {

    }

    @Override
    public void onCardCanceled() {

    }

    @Override
    public void onCardAppeared(View view, int position) {

    }

    @Override
    public void onCardDisappeared(View view, int position) {

    }
}