package br.com.william.a2pdm.util;

import android.os.Build;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import br.com.william.a2pdm.R;

@RequiresApi(api = Build.VERSION_CODES.O)
public class BindingAdapters {

    @BindingAdapter(value = "app:highlightIfSelected")
    public static void highlightIfSelected(ImageButton view, boolean selected) {
        if(selected) {
            view.setColorFilter(ContextCompat.getColor(view.getContext(), R.color.design_default_color_primary));
            view.setBackground(ContextCompat.getDrawable(view.getContext(), R.drawable.white_with_top_thick_stroke_primary));
        } else {
            view.setColorFilter(ContextCompat.getColor(view.getContext(), R.color.black));
            view.setBackground(null);
        }
    }

    @BindingAdapter("app:showIf")
    public static void showIf(View view, boolean show) {
        if(show) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

}
