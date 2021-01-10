package CustomAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.philonggear.nhahang.R;

import java.util.List;

import DTO.MonAnDTO;

public class AdapterDanhSachMonAn extends BaseAdapter {

    Context context;
    int layout;
    List<MonAnDTO> monAnDTOList;
    ViewHolderDanhSachMonAn viewHolderDanhSachMonAn;

    public AdapterDanhSachMonAn(Context context, int layout, List<MonAnDTO> monAnDTOList){
        this.context = context;
        this.layout = layout;
        this.monAnDTOList = monAnDTOList;
    }

    @Override
    public int getCount() {
        return monAnDTOList.size();
    }

    @Override
    public Object getItem(int i) {
        return monAnDTOList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return monAnDTOList.get(i).getMAMON();
    }
    public class ViewHolderDanhSachMonAn{
        ImageView imHinhMonAn;
        TextView txtTenMonAn,txtGia,txtMoTa;
    }
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if (view == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewHolderDanhSachMonAn = new ViewHolderDanhSachMonAn();
            view = layoutInflater.inflate(layout,viewGroup,false);

            viewHolderDanhSachMonAn.txtTenMonAn = view.findViewById(R.id.txtMaHoaDon);
            viewHolderDanhSachMonAn.txtGia = view.findViewById(R.id.txtMaBan);
            viewHolderDanhSachMonAn.txtMoTa = view.findViewById(R.id.txtTinhTrang);
            viewHolderDanhSachMonAn.imHinhMonAn = view.findViewById(R.id.image);

            view.setTag(viewHolderDanhSachMonAn);

        }else {
            viewHolderDanhSachMonAn = (ViewHolderDanhSachMonAn) view.getTag();
        }
        MonAnDTO monAnDTO = monAnDTOList.get(i);
        // chuyển byte -> bitmap
        byte[] hinhanh = monAnDTO.getHINHANH();
        if (hinhanh == null ||hinhanh.equals("")){
            viewHolderDanhSachMonAn.imHinhMonAn.setImageResource(R.drawable.mi1);
        }else {
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh,0,hinhanh.length);
            viewHolderDanhSachMonAn.imHinhMonAn.setImageBitmap(bitmap);
        }
        viewHolderDanhSachMonAn.txtTenMonAn.setText(monAnDTO.getTENMONAN());
        viewHolderDanhSachMonAn.txtGia.setText("Giá: "+monAnDTO.getGIATIEN()+" VNĐ");
        viewHolderDanhSachMonAn.txtMoTa.setText(monAnDTO.getMOTA());
        return view;
    }

}
