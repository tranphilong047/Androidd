package CustomAdapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.philonggear.nhahang.R;

import java.util.List;

import DAO.LoaiMonAnDAO;
import DTO.LoaiMonAnDTO;

public class AdapterHienThiLoaiMonAn extends BaseAdapter {

    Context context;
    int layout;
    List<LoaiMonAnDTO> loaiMonAnDTOList;
    ViewHolderLoaiMonAn viewHolderLoaiMonAn;

    public AdapterHienThiLoaiMonAn(Context context, int layout, List<LoaiMonAnDTO> loaiMonAnDTOList){
        this.context = context;
        this.layout = layout;
        this.loaiMonAnDTOList = loaiMonAnDTOList;
    }

    @Override
    public int getCount() {
        return loaiMonAnDTOList.size();
    }

    @Override
    public Object getItem(int i) {
        return loaiMonAnDTOList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return loaiMonAnDTOList.get(i).getMALOAI();
    }

    public class ViewHolderLoaiMonAn{
        TextView txtTenLoai;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewHolderLoaiMonAn = new ViewHolderLoaiMonAn();
            view = layoutInflater.inflate(R.layout.layout_customer_loai_mon_an,parent,false);
            viewHolderLoaiMonAn.txtTenLoai = (TextView) view.findViewById(R.id.spinTenLoai);
            view.setTag(viewHolderLoaiMonAn);
        }else {
            viewHolderLoaiMonAn = (ViewHolderLoaiMonAn) view.getTag();
        }
        LoaiMonAnDTO loaiMonAnDTO = loaiMonAnDTOList.get(position);
        viewHolderLoaiMonAn.txtTenLoai.setText(loaiMonAnDTO.getTENLOAI());
        viewHolderLoaiMonAn.txtTenLoai.setTag(loaiMonAnDTO.getMALOAI());
        return view;
    }
}
