package com.example.test_for_children.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_for_children.R;
import com.example.test_for_children.Test.OnlyTest;
import com.example.test_for_children.Test.Test;
import com.example.test_for_children.interfaces.PageEvents;
import com.example.test_for_children.ui.work.WorkFragment;

public class ViewPageAdapter extends RecyclerView.Adapter<ViewPageAdapter.ViewHolder> {

    private static final String TAG = "myLogs";

    private LayoutInflater mInflater;
    private PageEvents pageEvents;
    private Test test;

    public ViewPageAdapter(Context context, WorkFragment workFragment, Test test) {
        mInflater = LayoutInflater.from(context);
        this.pageEvents = workFragment;
        this.test = test;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.layout_page, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OnlyTest onlyTest = test.getOnlyTests()[position];

        String mQuestion = (position + 1) + ". Вопрос: " + onlyTest.getQuestion();

        String mAnswer_a = onlyTest.getAnswers()[0];
        String mAnswer_b = onlyTest.getAnswers()[1];
        String mAnswer_c = onlyTest.getAnswers()[2];
        String mAnswer_d = onlyTest.getAnswers()[3];

        holder.textView.setText(mQuestion);
        holder.btn_a.setText(mAnswer_a);
        holder.btn_b.setText(mAnswer_b);
        holder.btn_c.setText(mAnswer_c);
        holder.btn_d.setText(mAnswer_d);
    }

    @Override
    public int getItemCount() {
        return test.getOnlyTests().length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView textView;
        private final Button btn_a;
        private final Button btn_b;
        private final Button btn_c;
        private final Button btn_d;

        private final TextView text_answer_a_num;
        private final TextView text_answer_b_num;
        private final TextView text_answer_c_num;
        private final TextView text_answer_d_num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_question);

            btn_a = itemView.findViewById(R.id.text_answer_a);
            btn_b = itemView.findViewById(R.id.text_answer_b);
            btn_c = itemView.findViewById(R.id.text_answer_c);
            btn_d = itemView.findViewById(R.id.text_answer_d);

            btn_a.setOnClickListener(clik);
            btn_b.setOnClickListener(clik);
            btn_c.setOnClickListener(clik);
            btn_d.setOnClickListener(clik);

            text_answer_a_num = itemView.findViewById(R.id.text_answer_a_num);
            text_answer_b_num = itemView.findViewById(R.id.text_answer_b_num);
            text_answer_c_num = itemView.findViewById(R.id.text_answer_c_num);
            text_answer_d_num = itemView.findViewById(R.id.text_answer_d_num);
        }

        View.OnClickListener clik = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.text_answer_a:
                        clicked(v,0);
                        break;
                    case R.id.text_answer_b:
                        clicked(v,1);
                        break;
                    case R.id.text_answer_c:
                        clicked(v,2);
                        break;
                    case R.id.text_answer_d:
                        clicked(v,3);
                }
            }
        };

        private void clicked(View v, int index){
            btn_a.setBackgroundResource(R.drawable.text_answer);
            btn_b.setBackgroundResource(R.drawable.text_answer);
            btn_c.setBackgroundResource(R.drawable.text_answer);
            btn_d.setBackgroundResource(R.drawable.text_answer);

            text_answer_a_num.setBackgroundResource(R.drawable.text_question);
            text_answer_b_num.setBackgroundResource(R.drawable.text_question);
            text_answer_c_num.setBackgroundResource(R.drawable.text_question);
            text_answer_d_num.setBackgroundResource(R.drawable.text_question);

            v.setBackgroundResource(R.drawable.text_answer_select);

            switch (v.getId()){
                case R.id.text_answer_a: text_answer_a_num.setBackgroundResource(R.drawable.text_answer_select);
                    break;
                case R.id.text_answer_b: text_answer_b_num.setBackgroundResource(R.drawable.text_answer_select);
                    break;
                case R.id.text_answer_c: text_answer_c_num.setBackgroundResource(R.drawable.text_answer_select);
                    break;
                case R.id.text_answer_d: text_answer_d_num.setBackgroundResource(R.drawable.text_answer_select);
            }

            pageEvents.clicked(index);
        }
    }
}
