package com.rj.geeksnews.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.rj.geeksnews.R
import com.rj.geeksnews.databinding.CategoryListBinding
import com.rj.geeksnews.model.CategoryResponse

class CategoryAdapter(private val mListener:CategoryClickListener,val categoryList:ArrayList<CategoryResponse>):RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(CategoryListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
       categoryList.get(holder.adapterPosition)?.apply {
           holder.mBinding.apply {
               tvCategoryTitle.text= categoryTitle

               if (isSelected==true){
                 cvCategory.setCardBackgroundColor(ContextCompat.getColor(cvCategory.context, R.color.white))
                 tvCategoryTitle.setTextColor(ContextCompat.getColor(cvCategory.context, R.color.black))

               }else{
                   cvCategory.setCardBackgroundColor(ContextCompat.getColor(cvCategory.context, R.color.black))
                   tvCategoryTitle.setTextColor(ContextCompat.getColor(cvCategory.context, R.color.white))
               }
               cvCategory.setOnClickListener(){
                   val lastSelectedItem = getLastSelectedItem()
                   if (lastSelectedItem!=-1){
                       categoryList.get(lastSelectedItem).isSelected=false
                   }
                   categoryList.get(holder.adapterPosition).isSelected=true

                   setCategoryView(lastSelectedItem,holder.adapterPosition)
                   mListener.let {
                       mListener.onTabChange(id)
                   }
               }
           }
       }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    private fun setCategoryView(lastSelectedItem:Int,adapterPosition:Int){
        if (lastSelectedItem!=-1){
            notifyItemChanged(lastSelectedItem)
        }
        notifyItemChanged(adapterPosition)
    }
    private fun getLastSelectedItem():Int{
        for (i in 0 until (categoryList?.count()?:0))
        {
            if (categoryList.get(i).isSelected==true){
                return i
            }
        }
        return -1
    }

    interface CategoryClickListener{
        fun onTabChange(tabId:Int?)
    }
    inner class CategoryViewHolder(var mBinding: CategoryListBinding):RecyclerView.ViewHolder(mBinding.root)

}
