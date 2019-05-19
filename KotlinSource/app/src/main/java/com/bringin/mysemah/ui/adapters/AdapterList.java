package com.bringin.mysemah.ui.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bringin.mysemah.BaseActivity;
import com.bringin.mysemah.R;
import com.pengembangsebelah.model.JSON;
import com.pengembangsebelah.network.Function;
import com.pengembangsebelah.network.Result;
import org.jetbrains.annotations.NotNull;

public class AdapterList extends RecyclerView.Adapter<ViewHolder> {
    int code;
    JSON datas;
    Activity context;
    Result result;
    public AdapterList(JSON datas, int code,Activity context,Result result){
        this.code = code;
        this.datas=datas;
        this.context = context;
        this.result = result;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_nganu,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String f = String.valueOf(1+i);
        viewHolder.number.setText(f);
        if(code==1){
            viewHolder.title.setText(datas.getUser().get(0).getKeluhan().get(i).getKeluhan());
            viewHolder.date.setText(datas.getUser().get(0).getKeluhan().get(i).getDate());
        }else if(code==2){
            viewHolder.title.setText(datas.getUser().get(0).getObat().get(i).getNama_obat());
            viewHolder.date.setText(datas.getUser().get(0).getObat().get(i).getDate());
        }
        final int aaa = i;
        viewHolder.setOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("YAYA", "onClick: "+code);
                if(code==1){
                    Nganu1(datas.getUser().get(0).getKeluhan().get(aaa).getKeluhan(),datas.getUser().get(0).getKeluhan().get(aaa).getGambaran(),datas.getUser().get(0).getKeluhan().get(aaa).getId(),aaa);
                }else if(code==2){
                    Nganu2(datas.getUser().get(0).getObat().get(aaa).getNama_obat(),
                            datas.getUser().get(0).getObat().get(aaa).getJadwal_pemberian(),
                            datas.getUser().get(0).getObat().get(aaa).getKegunaan(),
                            datas.getUser().get(0).getObat().get(aaa).getDokter(),
                            datas.getUser().get(0).getObat().get(aaa).getEfek_samping(),
                            datas.getUser().get(0).getObat().get(aaa).getId(),
                            aaa);
                }
            }
        });
    }

    AlertDialog alertDialog;
    void Nganu1(String keluhan, String gambaran, final String id, final int pos){
        final AlertDialog.Builder buldozer = new AlertDialog.Builder(context);
        LayoutInflater inflater = context.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_nambah_list_keluhan, null);
        buldozer.setView(dialogView);

        AppCompatImageButton cancelBtn = dialogView.findViewById(R.id.cancel_dial);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        final AppCompatEditText keluhans = dialogView.findViewById(R.id.keluhan_dial);
        final AppCompatEditText gambarans = dialogView.findViewById(R.id.gambaran_dial);
        keluhans.setText(keluhan);
        gambarans.setText(gambaran);

        Button update = dialogView.findViewById(R.id.save_up_dial);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Function.UpdateKeluhan(BaseActivity.datas.getUser().get(0).getApikey(), keluhans.getText().toString(),
                        gambarans.getText().toString(), id, pos, new Result() {
                    @Override
                    public void Succes(@NotNull String message) {
                        alertDialog.dismiss();
                        result.Succes(message);
                    }

                    @Override
                    public void Failed(@NotNull String message) {
                        alertDialog.dismiss();
                        result.Failed(message);
                    }
                }).execute();
            }
        });

        Button delert = dialogView.findViewById(R.id.delet);
        delert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Function.DeleteKeluhan(BaseActivity.datas.getUser().get(0).getApikey(), id, pos, new Result() {
                    @Override
                    public void Succes(@NotNull String message) {
                        alertDialog.dismiss();
                        result.Succes(message);
                    }

                    @Override
                    public void Failed(@NotNull String message) {
                        alertDialog.dismiss();
                        result.Failed(message);
                    }
                }).execute();
            }
        });

        alertDialog = buldozer.create();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }
    void Nganu2(String a, String b,String c,String d,String e, final String id, final int pos){
        final AlertDialog.Builder buldozer = new AlertDialog.Builder(context);
        LayoutInflater inflater = context.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_nambah_obat, null);
        buldozer.setView(dialogView);

        AppCompatImageButton cancelBtn = dialogView.findViewById(R.id.cancel_diald);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        final AppCompatEditText aa = dialogView.findViewById(R.id.name_obat);
        final AppCompatEditText bb = dialogView.findViewById(R.id.jadwal_pwmbwerian);
        final AppCompatEditText cc = dialogView.findViewById(R.id.kegunaan);
        final AppCompatEditText dd = dialogView.findViewById(R.id.dokter);
        final AppCompatEditText ee = dialogView.findViewById(R.id.epek_samping);

        aa.setText(a);
        bb.setText(b);
        cc.setText(c);
        dd.setText(d);
        ee.setText(e);


        Button update = dialogView.findViewById(R.id.save_up_diald);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Function.UpdateObat(BaseActivity.datas.getUser().get(0).getApikey(),
                        aa.getText().toString(),
                        bb.getText().toString(),
                        cc.getText().toString(),
                        dd.getText().toString(),
                        ee.getText().toString(),
                        id, pos, new Result() {
                    @Override
                    public void Succes(@NotNull String message) {
                        alertDialog.dismiss();
                        result.Succes(message);
                    }

                    @Override
                    public void Failed(@NotNull String message) {
                        alertDialog.dismiss();
                        result.Succes(message);
                    }
                }).execute();
            }
        });

        Button delert = dialogView.findViewById(R.id.deletd);
        delert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Function.DeleteObat(BaseActivity.datas.getUser().get(0).getApikey(), id, pos, new Result() {
                    @Override
                    public void Succes(@NotNull String message) {
                        alertDialog.dismiss();
                        result.Succes(message);
                    }

                    @Override
                    public void Failed(@NotNull String message) {
                        alertDialog.dismiss();
                        result.Failed(message);
                    }
                }).execute();
            }
        });

        alertDialog = buldozer.create();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    @Override
    public int getItemCount() {
        if(code==1){
            if(datas.getUser().get(0).getKeluhan().size()>0){
                return datas.getUser().get(0).getKeluhan().size();
            }else {
                return 0;
            }
        }else if (code==2){
            if(datas.getUser().get(0).getObat().size()>0){
                return datas.getUser().get(0).getObat().size();
            }else {
                return 0;
            }
        }else {
            return 0;
        }
    }
}

class ViewHolder extends RecyclerView.ViewHolder{
    TextView title,number,date;
    View.OnClickListener onClickListener;
    public void setOnClick(View.OnClickListener onClickListener){
        this.onClickListener=onClickListener;
    }

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(v);
            }
        });
        title = itemView.findViewById(R.id.title_list);
        number = itemView.findViewById(R.id.number_list);
        date = itemView.findViewById(R.id.date_list);

    }

}
