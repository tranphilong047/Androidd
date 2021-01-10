package CustomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.philonggear.nhahang.R;

import java.util.List;

import DTO.MonAnDTO;
import DTO.NhanVienDTO;
import FragmentAPP.NhanVien;

public class AdapterDanhSachNhanVien extends BaseAdapter {

    Context context;
    int layout;
    List<NhanVienDTO> nhanVienDTOList;
    AdapterDanhSachNhanVien.ViewHolderDanhSachNhanVien viewHolderDanhSachNhanVien;

    public AdapterDanhSachNhanVien(Context context, int layout, List<NhanVienDTO> nhanVienDTOList){
        this.context = context;
        this.layout = layout;
        this.nhanVienDTOList = nhanVienDTOList;
    }

    @Override
    public int getCount() {
        return nhanVienDTOList.size();
    }

    @Override
    public Object getItem(int i) {
        return nhanVienDTOList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return nhanVienDTOList.get(i).getMANV();
    }

    public class ViewHolderDanhSachNhanVien{
        TextView txtHoTen,txtSoDienThoai,txtEmail;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view =convertView;
        if (view == null){
            viewHolderDanhSachNhanVien = new ViewHolderDanhSachNhanVien();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,viewGroup,false);

            viewHolderDanhSachNhanVien.txtHoTen = view.findViewById(R.id.extHoVaTen);
            viewHolderDanhSachNhanVien.txtSoDienThoai = view.findViewById(R.id.extSoDienThoai);
            viewHolderDanhSachNhanVien.txtEmail = view.findViewById(R.id.extEmail);

            view.setTag(viewHolderDanhSachNhanVien);
        }else {
            viewHolderDanhSachNhanVien = (ViewHolderDanhSachNhanVien) view.getTag();
        }
        NhanVienDTO nhanVienDTO = nhanVienDTOList.get(i);

        viewHolderDanhSachNhanVien.txtHoTen.setText((nhanVienDTO.getHOTEN()));
        viewHolderDanhSachNhanVien.txtSoDienThoai.setText((nhanVienDTO.getSDT()));
        viewHolderDanhSachNhanVien.txtEmail.setText((nhanVienDTO.getEMAIL()));
        return view;
    }
}
