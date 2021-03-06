package misc;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aayush.onlineshopping.ProductActivity;
import com.example.aayush.onlineshopping.R;
import com.example.aayush.onlineshopping.VendorItemPage;

import java.util.List;

public interface Adapters{
    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder> {
        private final Context current;
        private final List<Item> data;
        private final Cart cart;
        private final int id;

        public RecyclerViewAdapter(Context current, List<Item> data, Cart cart, int id) {
            this.current = current;
            this.data = data;
            this.cart = cart;
            this.id = id;
        }

        @NonNull
        @Override
        public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view;
            LayoutInflater inflater = LayoutInflater.from(current);
            view = inflater.inflate(R.layout.cardview_item, parent,false);
            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final CustomViewHolder holder, final int position) {
            holder.itemName.setText(data.get(holder.getAdapterPosition()).getName());
            holder.itemPrice.setText(data.get(holder.getAdapterPosition()).getPrice());
            holder.itemImage.setImageResource(data.get(holder.getAdapterPosition()).getImage());
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(current, ProductActivity.class);

                    intent.putExtra("name", data.get(holder.getAdapterPosition()).getName());
                    intent.putExtra("category",
                            data.get(holder.getAdapterPosition()).getCategory());
                    intent.putExtra("price",
                            data.get(holder.getAdapterPosition()).getPrice());
                    intent.putExtra("quantity",
                            data.get(holder.getAdapterPosition()).getQuantity());
                    intent.putExtra("image",
                            data.get(holder.getAdapterPosition()).getImage());
                    intent.putExtra("cart", cart);
                    intent.putExtra("id", id);

                    current.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        static class CustomViewHolder extends RecyclerView.ViewHolder {
            final TextView itemName;
            final TextView itemPrice;
            final ImageView itemImage;
            final CardView cardView;

            CustomViewHolder(View itemView){
                super(itemView);
                itemName = itemView.findViewById(R.id.tv_item_name);
                itemPrice = itemView.findViewById(R.id.tv_item_price);
                itemImage = itemView.findViewById(R.id.iv_item_image);
                cardView = itemView.findViewById(R.id.cv_layout);
            }
        }
    }

    class RVCartAdapter extends RecyclerView.Adapter<RVCartAdapter.CustomViewHolder> {
        private final Context current;
        private final Cart data;

        public RVCartAdapter(Context current, Cart data) {
            this.current = current;
            this.data = data;
        }

        @NonNull
        @Override
        public RVCartAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                 int viewType) {
            View view;
            LayoutInflater inflater = LayoutInflater.from(current);
            view = inflater.inflate(R.layout.cardview_cart_item, parent, false);
            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final CustomViewHolder holder, final int position) {
            holder.itemName.setText(data.getItemList().get(holder.getAdapterPosition()).getName());
            holder.itemCategory.setText(data.getItemList().get(holder.getAdapterPosition()).getCategory());
            holder.itemPrice.setText(data.getItemList().get(holder.getAdapterPosition()).getPrice());

            holder.itemQty.setText(String.valueOf(data.getItemList().get(holder.getAdapterPosition()).getQuantity()));
            holder.itemImage.setImageResource(data.getItemList().get(holder.getAdapterPosition()).getImage());
            holder.cardView.setEnabled(true);

            holder.itemRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    data.removeItem(data.getItemList().get(holder.getAdapterPosition()).getName(),
                            data.getItemList().get(holder.getAdapterPosition()).getCategory());
                    holder.cardView.setVisibility(View.GONE);
                }
            });
        }

        @Override
        public int getItemCount() {
            return data.getItemList().size();
        }

        public static class CustomViewHolder extends RecyclerView.ViewHolder {
            final TextView itemName;
            final TextView itemCategory;
            final TextView itemPrice;
            final TextView itemQty;
            final ImageView itemImage;
            final Button itemRemove;
            final CardView cardView;

            CustomViewHolder(View itemView){
                super(itemView);
                itemName = itemView.findViewById(R.id.tv_item_name);
                itemCategory = itemView.findViewById(R.id.tv_item_category);
                itemPrice = itemView.findViewById(R.id.tv_item_price);
                itemQty = itemView.findViewById(R.id.tv_item_quantity);
                itemImage = itemView.findViewById(R.id.iv_item_image);
                itemRemove = itemView.findViewById(R.id.btn_remove_from_cart);
                cardView = itemView.findViewById(R.id.cv_cart_item_layout);
            }
        }
    }

    class RVVendorAdapter extends RecyclerView.Adapter<RVVendorAdapter.CustomViewHolder> {
        private final Context current;
        private final List<Item> data;

        public RVVendorAdapter(Context current, List<Item> data){
            this.current = current;
            this.data = data;
        }

        @NonNull
        @Override
        public RVVendorAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                   int viewType) {
            View view;
            LayoutInflater inflater = LayoutInflater.from(current);
            view = inflater.inflate(R.layout.cardview_vendor_products, parent,false);
            return new RVVendorAdapter.CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final RVVendorAdapter.CustomViewHolder holder,
                                     int position) {
            holder.categoryName.setText(data.get(holder.getAdapterPosition()).getName());
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(current, VendorItemPage.class);
                    intent.putExtra("name", data.get(holder.getAdapterPosition()).getName());
                    intent.putExtra("category",
                            data.get(holder.getAdapterPosition()).getCategory());
                    intent.putExtra("price",
                            data.get(holder.getAdapterPosition()).getPrice());
                    intent.putExtra("quantity",
                            data.get(holder.getAdapterPosition()).getQuantity());
                    current.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        static class CustomViewHolder extends RecyclerView.ViewHolder {
            final TextView categoryName;
            final CardView cardView;

            CustomViewHolder(View itemView){
                super(itemView);
                categoryName = itemView.findViewById(R.id.tv_item_name);
                cardView = itemView.findViewById(R.id.cv_layout);
            }
        }
    }
}
