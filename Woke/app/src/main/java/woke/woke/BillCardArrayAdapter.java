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
 * Created by hana on 3/30/2017.
 */

public class BillCardArrayAdapter extends ArrayAdapter<Bill> {
    private static final String TAG = "CardArrayAdapter";
    private List<Bill> cardList = new ArrayList<Bill>();
    //there are 2 text views to edit so there will be 2 lines on each card.
    static class CardViewHolder {
        TextView line1;
        TextView line2;
    }

    public BillCardArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }
    //create a list of bills
    @Override
    public void add(Bill object) {
        cardList.add(object);
        super.add(object);
    }
    //how many bills have been added to this list
    @Override
    public int getCount() {
        return this.cardList.size();
    }
    //retrieve bills that have been added to list
    @Override
    public Bill getItem(int index) {
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
        Bill card = getItem(position);
        viewHolder.line1.setText(card.getTitle());
        viewHolder.line2.setText(card.getSponser());
        return row;
    }

    public Bitmap decodeToBitmap(byte[] decodedByte) {
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }

}
