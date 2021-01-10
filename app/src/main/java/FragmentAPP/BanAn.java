package FragmentAPP;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.philonggear.nhahang.R;
import com.philonggear.nhahang.ThemBanAnActivity;
import com.philonggear.nhahang.TrangChuActivity;

import java.util.List;

import CustomAdapter.AdapterHienThiBan;
import DAO.BanAnDAO;
import DTO.BanAnDTO;

public class BanAn extends Fragment {

    public static int RESQUEST_CODE_THEM = 111;

    GridView gvBanAn;
    List<BanAnDTO> banAnDTOList;
    BanAnDAO banAnDAO;
    AdapterHienThiBan adapterHienThiBan;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hien_thi_ban_an,container,false);
        setHasOptionsMenu(true);

        ((TrangChuActivity)getActivity()).getSupportActionBar().setTitle(R.string.ban_an);
        gvBanAn = (GridView) view.findViewById(R.id.HienThiBan);
        banAnDAO = new BanAnDAO(getActivity());

        HienThiBan();
        registerForContextMenu(gvBanAn);

        return view;
    }

    private void HienThiBan(){
        banAnDTOList = banAnDAO.DanhSachBanAn();
        adapterHienThiBan = new AdapterHienThiBan(getActivity(),R.layout.customer_gridview,banAnDTOList);
        gvBanAn.setAdapter(adapterHienThiBan);
        adapterHienThiBan.notifyDataSetChanged();
    }

    //Thêm Bàn
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem ThemBan = menu.add(1,R.id.ThemBanAn,1,R.string.themban);
        ThemBan.setIcon(R.drawable.thembanan);
        ThemBan.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.ThemBanAn:
                Intent intent = new Intent(getActivity(), ThemBanAnActivity.class);
                startActivityForResult(intent,RESQUEST_CODE_THEM);
                break;
        }
        return true;
    }
    //
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESQUEST_CODE_THEM){
            if (resultCode == Activity.RESULT_OK){
                Intent intent = data;
                boolean kiemtra = intent.getBooleanExtra("ketquathem",false);
                if (kiemtra){
                    HienThiBan();
                    Toast.makeText(getActivity(),getResources().getString(R.string.thembanthanhcong),Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getActivity(),getResources().getString(R.string.thembankhongthanhcong),Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
