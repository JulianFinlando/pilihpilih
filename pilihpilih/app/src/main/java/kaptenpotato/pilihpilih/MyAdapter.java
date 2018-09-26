package kaptenpotato.pilihpilih;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter  {
    private Context context;
    private int layout;
    private List<String> nama;
    private List<String> desc;

    public MyAdapter(Context context, int layout, List<String> nama, List<String> desc){
        this.context = context;
        this.layout = layout;
        this.nama = nama;
        this.desc = desc;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public void setnama(List<String> nama) {
        this.nama = nama;
    }

    public void setdesc(List<String> desc) {
        this.desc = desc;
    }

    @Override
    public int getCount() {
        return this.nama.size();
    }
    @Override
    public Object getItem(int position) {
        return this.nama.get(position);
    }
    @Override
    public long getItemId(int id) {
        return id;
    }

    public Context getContext() {
        return context;
    }

    public int getLayout() {
        return layout;
    }

    public List<String> getnama() {
        return nama;
    }

    public List<String> getdesc() {
        return desc;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        //Patron View holder
        ViewHolder holder;

        if(convertView == null){
            //View Sudah melewati batas
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(this.layout,null);
            holder = new ViewHolder();

            //Referensi elemen untuk memodifikasi dan mengisinya
            holder.nametextView = (TextView) convertView.findViewById(R.id.textView_nama);
            holder.origintextView = (TextView) convertView.findViewById(R.id.textView_desc);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        //Memberikan nilai tergantung dengan posisi
        String judulActual = nama.get(position);
        String descActual = desc.get(position);

        //Referensi elemen untuk memodifikasi dan mengisinya

        holder.nametextView.setText(judulActual);
        holder.origintextView.setText(descActual);
        return convertView;
    }

    static class ViewHolder {
        public TextView nametextView;
        public TextView origintextView;
    }
}
