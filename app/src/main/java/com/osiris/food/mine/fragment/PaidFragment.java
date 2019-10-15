package com.osiris.food.mine.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.osiris.food.R;
import com.osiris.food.base.BaseFragment;
import com.osiris.food.mine.adapter.OrderAllAdapter;
import com.osiris.food.model.OrderList;
import com.osiris.food.network.ApiRequestTag;
import com.osiris.food.network.NetRequest;
import com.osiris.food.network.NetRequestResultListener;
import com.osiris.food.utils.JsonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class PaidFragment extends BaseFragment {

    @BindView(R.id.rv_data)
    RecyclerView rv_data;
    @BindView(R.id.tv_nodata)
    TextView tvNodata;

    private List<OrderList.DataBean> dataList = new ArrayList<>();
    private OrderAllAdapter dataAdapter = new OrderAllAdapter(dataList);

    @Override
    protected int setLayout() {
        return R.layout.fragment_order_all;
    }

    @Override
    protected void initView() {


        rv_data.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
        rv_data.setAdapter(dataAdapter);
        dataAdapter.notifyDataSetChanged();
        getData();

    }

    @Override
    protected void initData() {

    }

    private void getData(){

        String url = ApiRequestTag.API_HOST + "/api/v1/users/order";

        NetRequest.requestNoParamWithToken(url, ApiRequestTag.REQUEST_DATA, new NetRequestResultListener() {

            @Override
            public void requestSuccess(int tag, String successResult) {

                JsonParser parser = new JsonParser();
                JsonObject json = parser.parse(successResult).getAsJsonObject();
                if (json.get("code").getAsInt() == 200){
                    OrderList.DataBean[] data = JsonUtils.fromJson(
                            json.get("data").getAsJsonArray(), OrderList.DataBean[].class);
                    if (dataList.size()>0) {
                        dataList.clear();
                    }

                    for (OrderList.DataBean dataBean:Arrays.asList(data)){
                        if (dataBean.getType().equals("已支付")){
                            dataList.add(dataBean);
                        }

                    }
                    dataAdapter.notifyDataSetChanged();

                    if (dataList.size() == 0){
                        tvNodata.setVisibility(View.VISIBLE);
                    }

                }
            }

            @Override
            public void requestFailure(int tag, int code, String msg) {

            }
        });


    }



}
