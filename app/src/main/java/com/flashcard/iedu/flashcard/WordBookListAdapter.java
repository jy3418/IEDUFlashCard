package com.flashcard.iedu.flashcard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flashcard.iedu.flashcard.R;

import java.util.List;

import edu.iedu.flashcard.dao.domain.Word;


public class WordBookListAdapter extends BaseAdapter {

    Context context = null;
    //List<String> mainColumn;
    //List<String> subColumn1;
    List<Word> cardList;
    boolean isShowFavorite;
    private static LayoutInflater inflater=null;

    public WordBookListAdapter(Context context, List<Word> cardList){
        this.context = context;
        this.cardList = cardList;
        isShowFavorite = true;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void showFavorite(boolean isShowFavorite){
        this.isShowFavorite = isShowFavorite;
    }

    @Override
    public int getCount() {
        return cardList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView;
        rowView = inflater.inflate(R.layout.activity_wordbook_list_item, null);
        TextView viewMain =(TextView) rowView.findViewById(R.id.textViewMain);
        TextView viewSub1 =(TextView) rowView.findViewById(R.id.textViewSub1);
        Button btnFavorite =(Button) rowView.findViewById(R.id.isFavorite);

        if(!isShowFavorite){
            LinearLayout btnFavoriteLayout =(LinearLayout) rowView.findViewById(R.id.isFavoriteLayout);
            btnFavoriteLayout.setVisibility(View.GONE);
        }

        viewMain.setText(this.cardList.get(position).getName());
        viewSub1.setText(this.cardList.get(position).getMeaning());


        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //TextView viewMain =(TextView) v.findViewById(R.id.textViewMain);
                //Toast.makeText(context, "You Clicked " + viewMain.getText(), Toast.LENGTH_LONG).show();
                ((WordBookListActivity)context).startCard();
            }
        });
        return rowView;
    }
}
