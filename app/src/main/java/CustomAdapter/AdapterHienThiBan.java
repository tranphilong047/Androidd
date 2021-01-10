package CustomAdapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.philonggear.nhahang.R;
import com.philonggear.nhahang.ThanhToanActivity;
import com.philonggear.nhahang.TrangChuActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import DAO.BanAnDAO;
import DAO.HoaDonDAO;
import DTO.BanAnDTO;
import DTO.HoaDonDTO;
import FragmentAPP.ThucDon;

public class AdapterHienThiBan extends BaseAdapter implements View.OnClickListener {

    Context context;
    int layout;
    List<BanAnDTO> banAnDTOList;
    ViewHolderBanAn viewHolderBanAn;
    BanAnDAO banAnDAO;
    HoaDonDAO hoaDonDAO;
    FragmentManager fragmentManager;

    public AdapterHienThiBan(Context context, int layout, List<BanAnDTO> banAnDTOList){
        this.context =context;
        this.banAnDTOList = banAnDTOList;
        this.layout = layout;
        banAnDAO =new BanAnDAO(context);
        hoaDonDAO = new HoaDonDAO(context);
        fragmentManager = ((TrangChuActivity)context).getSupportFragmentManager();
    }
    @Override
    public int getCount() {
        return banAnDTOList.size();
    }

    @Override
    public Object getItem(int position) {
        return banAnDTOList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return banAnDTOList.get(position).getMABAN();
    }



    public class ViewHolderBanAn{
        ImageView imBanAn,imThanhToan,imXoa,imGoiMon;
        TextView txtTenBan;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewHolderBanAn = new ViewHolderBanAn();
            view = layoutInflater.inflate(R.layout.customer_gridview,parent,false);
            viewHolderBanAn.imBanAn = (ImageView) view.findViewById(R.id.hinhBan);
            viewHolderBanAn.imGoiMon = (ImageView) view.findViewById(R.id.goimon);
            viewHolderBanAn.imThanhToan =(ImageView) view.findViewById(R.id.thanhtoanban);
            viewHolderBanAn.imXoa = (ImageView) view.findViewById(R.id.huyban);
            viewHolderBanAn.txtTenBan = (TextView) view.findViewById(R.id.tenbanAN);

            view.setTag(viewHolderBanAn);
        }else {
            viewHolderBanAn= (ViewHolderBanAn) view.getTag();
        }

        if (banAnDTOList.get(position).isChon()){
            moNut();
        }

        BanAnDTO banAnDTO = banAnDTOList.get(position);

        String tinhtrang = banAnDAO.TinhTrangBan(banAnDTO.getMABAN());
        if (tinhtrang.equals("true")){
            viewHolderBanAn.imBanAn.setImageResource(R.drawable.banantrue);
        }else {
            viewHolderBanAn.imBanAn.setImageResource(R.drawable.banan);
        }


        viewHolderBanAn.txtTenBan.setText(banAnDTO.getTENBAN());
        viewHolderBanAn.imBanAn.setTag(position);

        viewHolderBanAn.imBanAn.setOnClickListener(this);
        viewHolderBanAn.imGoiMon.setOnClickListener(this);
        viewHolderBanAn.imThanhToan.setOnClickListener(this);
        viewHolderBanAn.imXoa.setOnClickListener(this);

        return view;
    }
    private void moNut(){
        viewHolderBanAn.imGoiMon.setVisibility(View.VISIBLE);
        viewHolderBanAn.imThanhToan.setVisibility(View.VISIBLE);
        viewHolderBanAn.imXoa.setVisibility(View.VISIBLE);

        Animation animation = AnimationUtils.loadAnimation(context,R.anim.hieu_ung_hien_thi_ban);
        viewHolderBanAn.imGoiMon.startAnimation(animation);
        viewHolderBanAn.imThanhToan.startAnimation(animation);
        viewHolderBanAn.imXoa.startAnimation(animation);
    }
    private void dongNut(){
        Animation animation = AnimationUtils.loadAnimation(context,R.anim.hieu_ung_an_ban_an);
        viewHolderBanAn.imGoiMon.startAnimation(animation);
        viewHolderBanAn.imThanhToan.startAnimation(animation);
        viewHolderBanAn.imXoa.startAnimation(animation);

        viewHolderBanAn.imGoiMon.setVisibility(View.INVISIBLE);
        viewHolderBanAn.imThanhToan.setVisibility(View.INVISIBLE);
        viewHolderBanAn.imXoa.setVisibility(View.INVISIBLE);
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        viewHolderBanAn = (ViewHolderBanAn) ((View)view.getParent()).getTag();
        int vitriban = (int) viewHolderBanAn.imBanAn.getTag();
        int maban = banAnDTOList.get(vitriban).getMABAN();
        switch (id){
            case R.id.hinhBan:
                int vitri = (int) view.getTag();
                banAnDTOList.get(vitri).setChon(true);
                moNut();
                break;
            case R.id.goimon:
                Intent laytrangchu = ((TrangChuActivity)context).getIntent();
                int manhanvien = laytrangchu.getIntExtra("manhanvien",0);
                String tinhtrang = banAnDAO.TinhTrangBan(maban);
               if (tinhtrang.equals("false")){
                   Calendar calendar = Calendar.getInstance();
                   SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                   String ngaythanhtoan = dateFormat.format(calendar.getTime());

                   HoaDonDTO hoaDonDTO = new HoaDonDTO();
                   hoaDonDTO.setMABAN(maban);
                   hoaDonDTO.setMANV(manhanvien);
                   hoaDonDTO.setNGAYGOI(ngaythanhtoan);
                   hoaDonDTO.setTINHTRANG("false");

                   long kiemtra =  hoaDonDAO.ThemHoaDon(hoaDonDTO);
                   banAnDAO.CapNhatTinhTrangBan(maban,"true");
                   if (kiemtra == 0){
                       Toast.makeText(context, context.getResources().getString(R.string.thembankhongthanhcong),Toast.LENGTH_SHORT).show();
                   }
               }
                FragmentTransaction tranThucDon = fragmentManager.beginTransaction();
                ThucDon thucDonFragment = new ThucDon();
                Bundle bDuLieuThucDon = new Bundle();
                bDuLieuThucDon.putInt("maban",maban);

                thucDonFragment.setArguments(bDuLieuThucDon);

                tranThucDon.replace(R.id.content,thucDonFragment).addToBackStack("hienthiban");
                tranThucDon.commit();
                break;
            case R.id.thanhtoanban:
                Intent iThanhtoan = new Intent(context, ThanhToanActivity.class);
                iThanhtoan.putExtra("maban",maban);
                context.startActivity(iThanhtoan);
                break;
            case R.id.huyban:
                dongNut();
                break;
        }
    }

}
