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
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.philonggear.nhahang.DangKyActivity;
import com.philonggear.nhahang.R;
import com.philonggear.nhahang.ThemLoaiThucDonActivity;
import com.philonggear.nhahang.ThemThucDonActivity;
import com.philonggear.nhahang.TrangChuActivity;

import java.util.List;

import CustomAdapter.AdapterLoaiMonAnThucDon;
import DAO.LoaiMonAnDAO;
import DTO.LoaiMonAnDTO;

public class ThucDon extends Fragment {

    GridView gridView;
    List<LoaiMonAnDTO> loaiMonAnDTOS;
    LoaiMonAnDAO loaiMonAnDAO;
    FragmentManager fragmentManager;
    int maban;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hien_thi_thuc_don,container,false);
        setHasOptionsMenu(true);
        ((TrangChuActivity)getActivity()).getSupportActionBar().setTitle(R.string.thuc_don);

        gridView = (GridView) view.findViewById(R.id.gridviewloaithucdon);
        loaiMonAnDAO = new LoaiMonAnDAO(getActivity());

        fragmentManager = getActivity().getSupportFragmentManager();

        HienThiDanhSachLoai();
        registerForContextMenu(gridView);

        final Bundle bdulieuthucdon = getArguments();
        if (bdulieuthucdon != null){
            maban = bdulieuthucdon.getInt("maban");

        }
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    int maloai = loaiMonAnDTOS.get(i).getMALOAI();

                    DanhSachMonAn danhSachMonAn = new DanhSachMonAn();

                    Bundle bundle = new Bundle();
                    bundle.putInt("maloai",maloai);
                    bundle.putInt("maban",maban);

                    danhSachMonAn.setArguments(bundle);

                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.content,danhSachMonAn).addToBackStack("hienthiloai");
                    transaction.commit();

            }
        });
        return view;
    }
    private void HienThiDanhSachLoai(){
        loaiMonAnDTOS = loaiMonAnDAO.DanhSachMon();
        AdapterLoaiMonAnThucDon adapter = new AdapterLoaiMonAnThucDon(getActivity(),R.layout.customer_loai_mon_an,loaiMonAnDTOS);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
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
        int maLoai = loaiMonAnDTOS.get(vitri).getMALOAI();

        switch (id){
            case R.id.idSua:
                Intent iloaisp = new Intent(getActivity(), ThemLoaiThucDonActivity.class);
                iloaisp.putExtra("maloaisanpham",maLoai);
                startActivity(iloaisp);
                break;
            case R.id.idXoa:
                boolean kiemtra = loaiMonAnDAO.xoaLoaiMonAn(maLoai);
                if (kiemtra){
                    HienThiDanhSachLoai();
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
        MenuItem ThemLoaiMon = menu.add(1,R.id.ThemThucDon,1,R.string.themthucdon);
        ThemLoaiMon.setIcon(R.drawable.thucdon);
        ThemLoaiMon.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.ThemThucDon:
                Intent intent = new Intent(getActivity(), ThemThucDonActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }


}
