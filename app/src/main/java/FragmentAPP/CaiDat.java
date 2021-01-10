package FragmentAPP;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.philonggear.nhahang.R;
import com.philonggear.nhahang.TrangChuActivity;

import java.util.List;

import DAO.HoaDonDAO;
import DTO.HoaDonDTO;

public class CaiDat extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hien_thi_cai_dat,container,false);
        ((TrangChuActivity)getActivity()).getSupportActionBar().setTitle(R.string.caidat);

        return view;

    }
}
