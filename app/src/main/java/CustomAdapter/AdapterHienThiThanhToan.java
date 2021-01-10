package CustomAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.philonggear.nhahang.R;

import java.util.List;

import DTO.ThanhToanDTO;

public class AdapterHienThiThanhToan extends BaseAdapter {

    Context context;
    int layout;
    List<ThanhToanDTO> thanhToanDTOs;
    ViewHolderThanhToan viewHolderThanhToan;
    public AdapterHienThiThanhToan(Context context, int layout, List<ThanhToanDTO> thanhToanDTOs){
        this.context = context;
        this.layout = layout;
        this.thanhToanDTOs = thanhToanDTOs;
    }
    @Override
    public int getCount() {
        return thanhToanDTOs.size();
    }

    @Override
    public Object getItem(int i) {
        return thanhToanDTOs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public class ViewHolderThanhToan{
        TextView txtTenMonAn,txtSoLuong,txtGiaTien;
    }
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if (view == null){
            viewHolderThanhToan = new ViewHolderThanhToan();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,viewGroup,false);

            viewHolderThanhToan.txtTenMonAn = (TextView) view.findViewById(R.id.tenmonanThucDon);
            viewHolderThanhToan.txtSoLuong = (TextView) view.findViewById(R.id.soluongmonanthucdon);
            viewHolderThanhToan.txtGiaTien = (TextView) view.findViewById(R.id.giamonanThucDon);

            view.setTag(viewHolderThanhToan);
        }else {
            viewHolderThanhToan = (ViewHolderThanhToan) view.getTag();
        }


        ThanhToanDTO thanhToanDTO = thanhToanDTOs.get(i);
        viewHolderThanhToan.txtTenMonAn.setText(thanhToanDTO.getTENMONAN());
        viewHolderThanhToan.txtSoLuong.setText(String.valueOf(thanhToanDTO.getSOLUONG()));
        viewHolderThanhToan.txtGiaTien.setText(String.valueOf(thanhToanDTO.getGIATIEN()));
        return view;
    }
}
