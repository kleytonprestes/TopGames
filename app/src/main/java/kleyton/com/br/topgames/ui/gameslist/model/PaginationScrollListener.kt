package kleyton.com.br.topgames.ui.gameslist.model

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kleyton.com.br.topgames.CustomApplication.Companion.PAGE_SIZE

abstract class PaginationScrollListener(var layoutManager: GridLayoutManager) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (!isLastPage()) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                && firstVisibleItemPosition >= 0
                && totalItemCount >= PAGE_SIZE) {
                loadMoreItems()
            }
        }
    }



    protected abstract fun loadMoreItems()

    abstract fun isLastPage(): Boolean


}