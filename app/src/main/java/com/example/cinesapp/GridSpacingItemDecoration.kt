package com.example.cinesapp

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridSpacingItemDecoration(private val spanCount: Int, private val spacing: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view) // item position
        val column = position % spanCount // item column

        if (position >= spanCount) {
            outRect.top = spacing // item top
        }

        outRect.left = column * spacing / spanCount // column start
        outRect.right = spacing - (column + 1) * spacing / spanCount // column end
    }
}