package FragmentAPP;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.philonggear.nhahang.DangKyActivity;
import com.philonggear.nhahang.R;
import com.philonggear.nhahang.TrangChuActivity;

import java.util.List;

import CustomAdapter.AdapterDanhSachHoaDon;
import CustomAdapter.AdapterDanhSachNhanVien;
import DAO.HoaDonDAO;
import DAO.NhanVienDAO;
import DTO.HoaDonDTO;
import DTO.NhanVienDTO;

public class HoaDon extends Fragment {
    ListView listHoaDon;
    HoaDonDAO hoaDonDAO;
    List<HoaDonDTO> hoaDonDTOList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hien_thi_hoa_don,container,false);
        ((TrangChuActivity)getActivity()).getSupportActionBar().setTitle(R.string.thongke);
        listHoaDon = (ListView) view.findViewById(R.id.lvHoaDon);

        hoaDonDAO = new HoaDonDAO(getActivity());
        HienThiDanhSachHoaDon();

        registerForContextMenu(listHoaDon);

        return view;

    }
    private void HienThiDanhSachHoaDon(){
        hoaDonDTOList = hoaDonDAO.DanhSachHoaDon();
        AdapterDanhSachHoaDon adapterDanhSachHoaDon = new AdapterDanhSachHoaDon(getActivity(),R.layout.customer_danh_sach_hoa_don,hoaDonDTOList);
        listHoaDon.setAdapter(adapterDanhSachHoaDon);
        adapterDanhSachHoaDon.notifyDataSetChanged();
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.menu_xoa,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int vitri = menuInfo.position;
        int maHoaDon = hoaDonDTOList.get(vitri).getMAHOADON();

        switch (id){
            case R.id.idXoaMenu:
                boolean kiemtra = hoaDonDAO.xoaHoaDon(maHoaDon);
                if (kiemtra){
                    HienThiDanhSachHoaDon();
                    Toast.makeText(getActivity(),getActivity().getResources().getString(R.string.xoathanhcong),Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(),getActivity().getResources().getString(R.string.xoathatbai),Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return true;
    }

}
