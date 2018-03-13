package com.basic.nanodegree.booklisting;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ahmed AbdElQader on 13-Mar-18.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private final List<Book> mBooks;
    private final OnItemClickListener mListener;

    public BookAdapter(List<Book> books, OnItemClickListener listener){
        mBooks = books;
        mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(Book book);
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View bookView = LayoutInflater.from(context).inflate(R.layout.book_list_item, parent, false);
        BookViewHolder bookViewHolder = new BookViewHolder(bookView);
        return bookViewHolder;
    }


    @Override
    public void onBindViewHolder(BookAdapter.BookViewHolder holder, int position) {
        Book book = mBooks.get(position);
        holder.mBookTitle.setText(book.getBookTitle());
        holder.mBookAuthors.setText(book.generateStringOfAuthor());
        holder.mBookDescription.setText(book.getBookDescription());
        holder.bind(mBooks.get(position), mListener);
    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder {
        TextView mBookTitle;
        TextView mBookAuthors;
        TextView mBookDescription;

        public BookViewHolder(View v) {
            super(v);
            mBookTitle = (TextView) v.findViewById(R.id.book_title);
            mBookAuthors = (TextView) v.findViewById(R.id.book_authors);
            mBookDescription = (TextView) v.findViewById(R.id.book_description);
        }

        public void bind(final Book book, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(book);
                }
            });
        }
    }
}