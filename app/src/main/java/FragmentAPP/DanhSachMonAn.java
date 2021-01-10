package FragmentAPP;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.philonggear.nhahang.R;
import com.philonggear.nhahang.ThemSoLuongActivity;

import java.util.List;

import CustomAdapter.AdapterDanhSachMonAn;
import DAO.MonAnDAO;
import DTO.MonAnDTO;

public class DanhSachMonAn extends Fragment {

    GridView gridView;
    MonAnDAO monAnDAO;
    List<MonAnDTO> monAnDTOList;
    AdapterDanhSachMonAn adapterDanhSachMonAn;
    int maban;
    int maloai;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_danh_sach_mon_an,container,false);
        gridView = view.findViewById(R.id.danhsachmon);

        monAnDAO = new MonAnDAO(getActivity());


        Bundle bundle = getArguments();
        if (bundle != null){
            maloai = bundle.getInt("maloai");
            maban = bundle.getInt("maban");

            HienThiDanhSachMon();

            registerForContextMenu(gridView);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if (maban !=0){
                        Intent isoluong = new Intent(getActivity(), ThemSoLuongActivity.class);
                        isoluong.putExtra("maban",maban);
                        isoluong.putExtra("mamonan",monAnDTOList.get(i).getMAMON());

                        startActivity(isoluong);
                    }
                }
            });

        }
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN){
                    getFragmentManager().popBackStack("hienthiloai", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
                return false;
            }
        });
        return view;
    }
    private void HienThiDanhSachMon(){
        monAnDTOList = monAnDAO.DanhSachMonAn(maloai);
        adapterDanhSachMonAn = new AdapterDanhSachMonAn(getActivity(),R.layout.customer_danh_sach_thuc_don,monAnDTOList);
        gridView.setAdapter(adapterDanhSachMonAn);
        adapterDanhSachMonAn.notifyDataSetChanged();
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
        int maMonAn = monAnDTOList.get(vitri).getMAMON();
        switch (id){
            case R.id.idXoaMenu:
                boolean kiemtra = monAnDAO.xoaMonAn(maMonAn);
                if (kiemtra){
                    HienThiDanhSachMon();
                    Toast.makeText(getActivity(),getActivity().getResources().getString(R.string.xoathanhcong),Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(),getActivity().getResources().getString(R.string.xoathatbai),Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return true;
    }
}
