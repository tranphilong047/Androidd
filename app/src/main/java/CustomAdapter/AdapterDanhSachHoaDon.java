package CustomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.philonggear.nhahang.R;

import java.util.List;

import DTO.HoaDonDTO;
import DTO.NhanVienDTO;

public class AdapterDanhSachHoaDon extends BaseAdapter {

    Context context;
    int layout;
    List<HoaDonDTO> hoaDonDTOList;
    AdapterDanhSachHoaDon.ViewHolderDanhSachHoaDon viewHolderDanhSachHoaDon;

    public AdapterDanhSachHoaDon(Context context, int layout, List<HoaDonDTO> hoaDonDTOList){
        this.context = context;
        this.layout = layout;
        this.hoaDonDTOList = hoaDonDTOList;
    }

    @Override
    public int getCount() {
        return hoaDonDTOList.size();
    }

    @Override
    public Object getItem(int i) {
        return hoaDonDTOList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return hoaDonDTOList.get(i).getMAHOADON();
    }
    public class ViewHolderDanhSachHoaDon{
        TextView txtMaHoaDon,txtMaBan,txtMaNhanVien,txtTinhTrang,txtNgayTao;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view =convertView;
        if (view == null){
            viewHolderDanhSachHoaDon = new ViewHolderDanhSachHoaDon();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,viewGroup,false);

            viewHolderDanhSachHoaDon.txtMaHoaDon = view.findViewById(R.id.txtMaHoaDon);
            viewHolderDanhSachHoaDon.txtMaBan = view.findViewById(R.id.txtMaBan);
            viewHolderDanhSachHoaDon.txtMaNhanVien = view.findViewById(R.id.txtMaNhanVien);
            viewHolderDanhSachHoaDon.txtNgayTao = view.findViewById(R.id.txtNgayTao);
            viewHolderDanhSachHoaDon.txtTinhTrang = view.findViewById(R.id.txtTinhTrang);

            view.setTag(viewHolderDanhSachHoaDon);
        }else {
            viewHolderDanhSachHoaDon = (AdapterDanhSachHoaDon.ViewHolderDanhSachHoaDon) view.getTag();
        }
        HoaDonDTO hoaDonDTO = hoaDonDTOList.get(i);



        viewHolderDanhSachHoaDon.txtMaHoaDon.setText((hoaDonDTO.getMAHOADON())+"");
        viewHolderDanhSachHoaDon.txtMaBan.setText((hoaDonDTO.getMABAN())+"");
        viewHolderDanhSachHoaDon.txtMaNhanVien.setText((hoaDonDTO.getMANV())+"");
        viewHolderDanhSachHoaDon.txtNgayTao.setText((hoaDonDTO.getNGAYGOI()));
        viewHolderDanhSachHoaDon.txtTinhTrang.setText((hoaDonDTO.getTINHTRANG()));
        return view;
    }
}
