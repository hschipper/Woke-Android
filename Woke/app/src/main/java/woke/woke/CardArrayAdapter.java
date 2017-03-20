package woke.woke;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by hana on 3/19/2017.
 *
 * This class is used to display cards on the app
 */
                                        //Member is MY java class.
public class CardArrayAdapter extends ArrayAdapter<Member> {
    private static final String TAG = "CardArrayAdapter";
    private List<Member> cardList = new ArrayList<Member>();
    //there are 2 text views to edit so there will be 2 lines on each card.
    static class CardViewHolder {
        TextView line1;
        TextView line2;
    }

    public CardArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }
    //create a list of members
    @Override
    public void add(Member object) {
        cardList.add(object);
        super.add(object);
    }
    //how many members have been added to this list
    @Override
    public int getCount() {
        return this.cardList.size();
    }
    //retrieve members that have been added to list
    @Override
    public Member getItem(int index) {
        return this.cardList.get(index);
    }
    //display each card and edit view to correct member information
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        CardViewHolder viewHolder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item_card, parent, false);
            viewHolder = new CardViewHolder();
            viewHolder.line1 = (TextView) row.findViewById(R.id.line1);
            viewHolder.line2 = (TextView) row.findViewById(R.id.line2);
            row.setTag(viewHolder);
        } else {
            viewHolder = (CardViewHolder)row.getTag();
        }
        Member card = getItem(position);
        viewHolder.line1.setText(card.getMember());
        viewHolder.line2.setText(card.getState());
        return row;
    }

    public Bitmap decodeToBitmap(byte[] decodedByte) {
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }

}
