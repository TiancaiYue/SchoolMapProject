package com.keyuan.schoolmap.adapter;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.base.BaseRecyclerAdapter;
import com.keyuan.schoolmap.common.Utils;
import com.keyuan.schoolmap.entify.ApplyForReimbursement;
import com.keyuan.schoolmap.entify.OptionsPicker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zmy on 2017/9/21.
 */

public class SubmitExpenseAdapter extends BaseRecyclerAdapter<ApplyForReimbursement, SubmitExpenseAdapter.ViewHolder> implements View.OnClickListener {
    private List<OptionsPicker> expenseAccountType = new ArrayList<>();
    private List<String> expenseAccountItems = new ArrayList<>();
    private List<String> nullArray = new ArrayList<>();
    private OptionsPickerView chooseView;
    private onItemClick onItemClick;
    private EditorAction onEditorAction;

    public SubmitExpenseAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_submit_expense_account, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        ApplyForReimbursement applyForReimbursement = mDatas.get(position);
        holder.tvType.setText(applyForReimbursement.getTypeName());
        holder.tvType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //先关闭软键盘
                Utils.hideSoftKeyBoard((Activity) mContext);
                if (expenseAccountType != null && expenseAccountType.size() > 0) {
                    initStateChoose(position);
                    chooseView.show();
                }
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatas.remove(position);
                notifyDataSetChanged();
            }
        });
        // 报销金额
        holder.etPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0, s.toString().indexOf(".") + 3);
                        holder.etPrice.setText(s);
                        holder.etPrice.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    holder.etPrice.setText(s);
                    holder.etPrice.setSelection(2);
                }
                if (s.toString().startsWith("0") && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        holder.etPrice.setText(s.subSequence(0, 1));
                        holder.etPrice.setSelection(1);
                        return;
                    }
                }
                if (s.length() > 0) {
                    applyForReimbursement.setPrice(s.toString().trim());
                } else {
                    applyForReimbursement.setPrice("");
                }
                if (onEditorAction != null) {
                    onEditorAction.onEditorAction();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        // 费用说明
        holder.etDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                applyForReimbursement.setDescription(s.toString().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        //((ViewHolder) holder).etPrice.setOnEditorActionListener(this);
        holder.itemView.setTag(position);
    }

    public void setOnItemClickListener(onItemClick listener) {
        onItemClick = listener;
    }

    public interface onItemClick {
        void onItemClick(View view, int position);
    }

    public void setOnEditorActionListener(EditorAction listener) {
        onEditorAction = listener;
    }

    public interface EditorAction {
        void onEditorAction();
    }

    @Override
    public void onClick(View view) {
        int pos = (int) view.getTag();
        if (onItemClick != null) {
            onItemClick.onItemClick(view, pos);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private EditText etPrice;
        private EditText etDescription;
        private TextView tvType;
        private Button btnDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            etPrice = (EditText) itemView.findViewById(R.id.et_price);
            etDescription = (EditText) itemView.findViewById(R.id.et_description);
            tvType = (TextView) itemView.findViewById(R.id.tv_type);
            btnDelete = (Button) itemView.findViewById(R.id.btn_delete);
        }
    }

    private void initStateChoose(final int position) {
        chooseView = new OptionsPickerView.Builder(mContext, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                int typeId = expenseAccountType.get(options2).getId();
                String typeName = expenseAccountType.get(options2).getName();
                mDatas.get(position).setTypeId(typeId);
                mDatas.get(position).setTypeName(typeName);
                notifyDataSetChanged();
            }
        })
                .setCancelColor(Color.parseColor("#b99042"))
                .setSubmitColor(Color.parseColor("#b99042")).build();
        chooseView.setNPicker(nullArray, expenseAccountItems, nullArray);
    }

    public void setExpenseAccountType(List<OptionsPicker> expenseAccountType) {
        this.expenseAccountType = expenseAccountType;
        for (OptionsPicker optionsPicker :
                expenseAccountType) {
            expenseAccountItems.add(optionsPicker.getName());
        }
    }
}
