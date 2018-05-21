package pesticides_map;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import pesticides_map.DataLayer.Category;
import pesticides_map.DataLayer.Coordinate;
import pesticides_map.DataLayer.DatabaseHelper;
import com.example.arunn.silfraagri.R;



public class CategoryListViewAdapter extends RecyclerView.Adapter<CategoryListViewAdapter.ViewHolder> {
    private List<Category> categoryDataset;
    private Context context;
    private Coordinate extraCoordinates;


    public CategoryListViewAdapter(Context context, List<Category> items, Coordinate extraCoordinates) {

        this.categoryDataset = items;
        this.context = context;
        this.extraCoordinates = extraCoordinates;
    }


    public CategoryListViewAdapter(Context context, List<Category> items) {

        this.categoryDataset = items;
        this.context = context;
    }


    @Override
    public CategoryListViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (context instanceof SaveSpotCategoryActivity) {

            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card_view_layout_category, parent, false);
            ViewHolder viewHolder = new ViewHolder(v, context, categoryDataset);
            return viewHolder;
        }

        else {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.edit_category_card_view_layout, parent, false);
            ViewHolder viewHolder = new ViewHolder(v, context, categoryDataset);
            return viewHolder;
        }
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if(holder.isCategoryCollection) {
            holder.catNameTextView.setText(categoryDataset.get(position).getCategoryName());
            holder.catImageView.setImageResource(categoryDataset.get(position).getCategoryImg());
            final AlertDialog.Builder confirmationWindowBuilder = createDeleteCategoryDialog(position);
            //TODO: CHECK IF DELETABLE INNAN DELETEKNAPPEN VISAS! 0 = FALSE
            holder.catDeleteImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   AlertDialog alertDialog = confirmationWindowBuilder.create();
                   alertDialog.show();
                }
            });
        }
        else if (!holder.isCategoryCollection) {

            holder.catNameTextView.setText(categoryDataset.get(position).getCategoryName());
            holder.catImageView.setImageResource(categoryDataset.get(position).getCategoryImg());

            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                /**
                 * Metod körs om ett objekt i ViewHolder klickas på
                 *
                 * @param v
                 */
                @Override
                public void onClick(View v) {

                    Category aCategory = categoryDataset.get(position);
                    int id = aCategory.getCategoryId();
                    String name = aCategory.getCategoryName();
                    int img = aCategory.getCategoryImg();
                    int delete = aCategory.getIsDeletable();

                    Intent intent = new Intent(context, SaveTitleActivity.class);
                    intent.putExtra("EXTRA_MESSAGE_CATEGORY_ID", id);
                    intent.putExtra("EXTRA_MESSAGE_CATEGORY_NAME", name);
                    intent.putExtra("EXTRA_MESSAGE_CATEGORY_IMG", img);
                    intent.putExtra("EXTRA_MESSAGE_CATEGORY_IS_DELETABLE", delete);

                    Coordinate coordinate = new Coordinate();
                    coordinate.putExtraMessageCoordinate(intent, extraCoordinates);

                    context.startActivity(intent);
                }
            });
        }
    }


    private AlertDialog.Builder createDeleteCategoryDialog(final int position) {
        AlertDialog.Builder confirmationDialog = new AlertDialog.Builder(context);
        confirmationDialog.setTitle(R.string.delete_category);
        confirmationDialog.setMessage(R.string.delete_confirmation_category + " " + categoryDataset.get(position).getCategoryName() + "?");


        confirmationDialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                DatabaseHelper.deleteCategory(context, categoryDataset.get(position));
                Toast.makeText(context, categoryDataset.get(position).getCategoryName() + " " + R.string.has_deleted, Toast.LENGTH_SHORT).show();
                deleteCategory(position);
                dialog.dismiss();
            }
        });


        confirmationDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {


                dialog.dismiss();
            }
        });

        return confirmationDialog;
    }





    @Override
    public int getItemCount() {
        return categoryDataset.size();
    }


    @Override
    public int getItemViewType(int position) {
        return 0;
    }


    public void insertCategory(Category category) {
        categoryDataset.add(category);

        notifyItemInserted(categoryDataset.size());
    }


    public void deleteCategory(int position){
        categoryDataset.remove(position);
        notifyItemRemoved(position);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView catNameTextView;
        ImageView catImageView;
        ImageView catDeleteImageView;
        ImageView catEditImageView;
        RelativeLayout relativeLayout;
        List<Category> categories;
        Context context;

        boolean isCategoryCollection;

        public ViewHolder(View v, Context context, List<Category> category) {
            super(v);
            this.categories = category;
            this.context = context;
            if (v.findViewById(R.id.relativeLayout_category_collection) != null){
                catNameTextView = (TextView) v.findViewById(R.id.category_name_cardview);
                catImageView = (ImageView) v.findViewById(R.id.category_image_cardview);
                catDeleteImageView = v.findViewById(R.id.delete_category_icon);
                catEditImageView = v.findViewById(R.id.edit_category_icon);
                relativeLayout = (RelativeLayout) v.findViewById(R.id.relativeLayout_category_collection);
                isCategoryCollection = true;
            }
            else if(v.findViewById(R.id.relativeLayout) != null){
                catNameTextView = (TextView) v.findViewById(R.id.category_name_cardview);
                catImageView = (ImageView) v.findViewById(R.id.category_image_cardview);
                relativeLayout = (RelativeLayout) v.findViewById(R.id.relativeLayout);
                isCategoryCollection = false;
            }

        }

    }
}

