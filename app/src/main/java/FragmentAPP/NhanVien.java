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

import CustomAdapter.AdapterDanhSachNhanVien;
import DAO.NhanVienDAO;
import DTO.NhanVienDTO;

public class NhanVien extends Fragment {

    ListView listNhanVien;
    NhanVienDAO nhanVienDAO;
    List<NhanVienDTO> nhanVienDTOList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hien_thi_nhan_vien,container,false);
        setHasOptionsMenu(true);
        ((TrangChuActivity)getActivity()).getSupportActionBar().setTitle(R.string.nhanvien);
        listNhanVien = (ListView) view.findViewById(R.id.lvHoaDon);

        nhanVienDAO = new NhanVienDAO(getActivity());
        HienThiDanhSachNhanVien();

        registerForContextMenu(listNhanVien);

        return view;

    }
    private void HienThiDanhSachNhanVien(){
        nhanVienDTOList = nhanVienDAO.DanhSachNhanVien();

        AdapterDanhSachNhanVien adapterDanhSachNhanVien = new AdapterDanhSachNhanVien(getActivity(),R.layout.customer_danh_sach_nhan_vien,nhanVienDTOList);
        listNhanVien.setAdapter(adapterDanhSachNhanVien);
        adapterDanhSachNhanVien.notifyDataSetChanged();
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.edit_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int vitri = menuInfo.position;
        int maNhanVien = nhanVienDTOList.get(vitri).getMANV();

        switch (id){
            case R.id.idSua:
                Intent idangky = new Intent(getActivity(),DangKyActivity.class);
                idangky.putExtra("manhanvien",maNhanVien);
                startActivity(idangky);
                break;
            case R.id.idXoa:
                boolean kiemtra = nhanVienDAO.xoaNhanVien(maNhanVien);
                if (kiemtra){
                    HienThiDanhSachNhanVien();
                    Toast.makeText(getActivity(),getActivity().getResources().getString(R.string.xoathanhcong),Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(),getActivity().getResources().getString(R.string.xoathatbai),Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return true;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem ThemLoaiMon = menu.add(1,R.id.ThemNhanVien,1,R.string.themnhanvien);
        ThemLoaiMon.setIcon(R.drawable.nhanvien);
        ThemLoaiMon.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.ThemNhanVien:
                Intent intent = new Intent(getActivity(), DangKyActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}
