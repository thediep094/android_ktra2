package com.example.thuchanh2.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.thuchanh2.R;
import com.example.thuchanh2.UpdateActivity;
import com.example.thuchanh2.model.CongViec;

import java.util.List;

public class CongViecAdapter extends ArrayAdapter {
    Context context;
    List<CongViec> congViecs;
    private CongViecItemListener congViecItemListener;
    public CongViecAdapter(@NonNull Context context, @NonNull List congViecs) {
        super(context, R.layout.item, congViecs);

        this.context = context;
        this.congViecs = congViecs;
    }

    public void setCongViecItemListener(CongViecItemListener congViecItemListener) {
        this.congViecItemListener = congViecItemListener;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, null, true);
        TextView txtTen = v.findViewById(R.id.textViewTen);
        TextView txtNoiDung = v.findViewById(R.id.textViewNoiDung);
        TextView txtNgay = v.findViewById(R.id.textViewNgay);
        TextView txtCongTac = v.findViewById(R.id.textViewCongTac);
        TextView txtTinhTrang = v.findViewById(R.id.textViewTinhTrang);

        CongViec congViec = congViecs.get(position);
        txtTen.setText(congViec.getTen());
        txtNoiDung.setText(congViec.getNoidung());
        txtNgay.setText(congViec.getNgayHoanThanh());
        switch (congViec.getTinhtrang()) {
            case 0:
                txtTinhTrang.setText("Chưa thực hiện");
                break;
            case 1:
                txtTinhTrang.setText("Đang thực hiện");
                break;
            case 2:
                txtTinhTrang.setText("Hoàn thành");
                break;
        }

        if (congViec.isCongTac()) {
            txtCongTac.setText("Làm chung");
        } else {
            txtCongTac.setText("1 mình");
        }

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                congViecItemListener.onItemClicked(v, position);
            }
        });

        return v;
    }

    //Item clicked sent to Main
    public interface CongViecItemListener {
        void onItemClicked(View v, int position);
    }
}
