package com.flashcard.iedu.flashcard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.flashcard.iedu.flashcard.R;

import java.util.List;

import edu.iedu.flashcard.dao.domain.Word;


public class ResultAdapter extends BaseAdapter {

    Context context = null;
    //List<String> mainColumn;
    //List<String> subColumn1;
    List<Word> cardList;
    private static LayoutInflater inflater=null;

    public ResultAdapter(Context context, List<Word> cardList){
        this.context = context;
        this.cardList = cardList;

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        //activity_wordbook_list_item also works for resultadapter; basically same thing
        TextView viewMain =(TextView) rowView.findViewById(R.id.textViewMain);
        TextView viewSub1 =(TextView) rowView.findViewById(R.id.textViewSub1);
        viewMain.setText(this.cardList.get(position).getName());
        viewSub1.setText(this.cardList.get(position).getMeaning());


        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                TextView viewMain =(TextView) v.findViewById(R.id.textViewMain);
                Toast.makeText(context, "You Clicked " + viewMain.getText(), Toast.LENGTH_LONG).show();
            }
        });
        return rowView;
    }
}

