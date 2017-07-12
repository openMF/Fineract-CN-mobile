package com.mifos.apache.fineract.ui.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mifos.apache.fineract.R;
import com.mifos.apache.fineract.data.models.deposit.CustomerDepositAccounts;
import com.mifos.apache.fineract.injection.ApplicationContext;
import com.mifos.apache.fineract.ui.base.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Rajan Maurya
 *         On 07/07/17.
 */
public class CustomerDepositAdapter extends
        RecyclerView.Adapter<CustomerDepositAdapter.ViewHolder> {

    private Context context;
    private List<CustomerDepositAccounts> customerDepositAccounts;
    public OnItemClickListener onItemClickListener;

    @Inject
    public CustomerDepositAdapter(@ApplicationContext Context context) {
        this.context = context;
        customerDepositAccounts = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_customer_deposit_accounts, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CustomerDepositAccounts customerDepositAccount = customerDepositAccounts.get(position);

        holder.tvCustomerAccountIdentifier.setText(customerDepositAccount.getAccountIdentifier());
        holder.tvDepositProduct.setText(customerDepositAccount.getProductIdentifier());
        holder.tvAccountBalance.setText(String.valueOf(customerDepositAccount.getBalance()));

        switch (customerDepositAccount.getState()) {
            case ACTIVE:
                holder.ivStatusIndicator.setColorFilter(
                        ContextCompat.getColor(context, R.color.deposit_green));
                holder.tvAccountBalance.setTextColor(
                        ContextCompat.getColor(context, R.color.deposit_green));
                break;
            case CLOSED:
                holder.ivStatusIndicator.setColorFilter(
                        ContextCompat.getColor(context, R.color.black));
                break;
            case LOCKED:
                holder.ivStatusIndicator.setColorFilter(
                        ContextCompat.getColor(context, R.color.black));
                break;
            case PENDING:
                holder.ivStatusIndicator.setColorFilter(
                        ContextCompat.getColor(context, R.color.light_yellow));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return customerDepositAccounts.size();
    }

    public void setCustomerDepositAccounts(List<CustomerDepositAccounts> customerDepositAccounts) {
        this.customerDepositAccounts = customerDepositAccounts;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        onItemClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnLongClickListener {

        @BindView(R.id.iv_status_indicator)
        ImageView ivStatusIndicator;

        @BindView(R.id.tv_customer_account_identifier)
        TextView tvCustomerAccountIdentifier;

        @BindView(R.id.tv_deposit_product)
        TextView tvDepositProduct;

        @BindView(R.id.tv_account_balance)
        TextView tvAccountBalance;

        @BindView(R.id.ll_customer_deposit_accounts)
        LinearLayout llCustomerDepositAccounts;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
            llCustomerDepositAccounts.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v, getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            onItemClickListener.onItemLongPress(v, getAdapterPosition());
            return true;
        }
    }
}