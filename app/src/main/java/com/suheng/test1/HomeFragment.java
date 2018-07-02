package com.suheng.test1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
//import android.app.Fragment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class HomeFragment extends Fragment {

    private final int HANDLER_MSG_TELL_RECV = 0x124;
    private EditText client_host_ip, client_port, positionx,positiony,positionz,orientationx,orientationy,orientationz,orientationw,action;
    private Button client_submit,send_action;

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            //设置一个弹框
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("python服务器的数据显示：" + msg.obj);
            //创建弹框 并展示
            builder.create().show();
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //R.layout.fragment_my为该fragment的布局
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        initEvent();
    }
    private void initEvent() {

        client_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String host = client_host_ip.getText().toString();
                String port = client_port.getText().toString();

                String content = positionx.getText().toString()
                        +"," + positiony.getText().toString()
                        +"," + positionz.getText().toString()
                        +"," + orientationx.getText().toString()
                        +"," + orientationy.getText().toString()
                        +"," + orientationz.getText().toString()
                        +"," + orientationw.getText().toString();
                //String host = "192.168.199.110";
                //String port = "8088";
                //String content = "123";
                Toast.makeText(getActivity(),host+","+port+","+content,Toast.LENGTH_LONG).show();
                //启动网络线程处理数据
                startNetThread(host,Integer.parseInt(port),content);

            }
        });

        send_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String action_no = action.getText().toString();
                String host = client_host_ip.getText().toString();
                Toast.makeText(getActivity(),host+","+"9099"+","+action_no,Toast.LENGTH_LONG).show();
                startNetThread(host,Integer.parseInt("9099"),action_no);
            }
        });
    }

    private void startNetThread(final String host, final int port, final String data) {
        new Thread() {
            public void run() {
                try {
                    //创建客户端对象
                    Socket socket = new Socket(host, port);
                    //获取客户端对象的输出流
                    OutputStream outputStream = socket.getOutputStream();
                    //把内容以字节流的形式写入(data).getBytes();
                    outputStream.write(data.getBytes());
                    //刷新流管道
                    outputStream.flush();
                    System.out.println("打印客户端中的内容：" + socket);
                    //拿到客户端输入流
                    InputStream is = socket.getInputStream();

                    byte[] bytes = new byte[1024];
                    //回应数据
                    int n = is.read(bytes);
                    System.out.println(new String(bytes, 0, n));
                    Message msg = handler.obtainMessage(HANDLER_MSG_TELL_RECV, new String(bytes, 0, n));
                    msg.sendToTarget();
                    //关闭流
                    is.close();
                    //关闭客户端
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
//启动线程
        }.start();
    }



//初始化控件
private void initViews() {
        client_host_ip =  (EditText)getActivity().findViewById(R.id.client_host_ip);
        client_port =  (EditText)getActivity().findViewById(R.id.client_port_ip);

        positionx = (EditText)getActivity().findViewById(R.id.positionX);
        positiony = (EditText)getActivity().findViewById(R.id.positionY);
        positionz = (EditText)getActivity().findViewById(R.id.positionZ);
        orientationx = (EditText)getActivity().findViewById(R.id.orientationX);
        orientationy = (EditText)getActivity().findViewById(R.id.orientationY);
        orientationz = (EditText)getActivity().findViewById(R.id.orientationZ);
        orientationw = (EditText)getActivity().findViewById(R.id.orientationW);
        action=(EditText)getActivity().findViewById(R.id.action);
        client_submit = (Button)getActivity().findViewById(R.id.client_submit);
        send_action=(Button)getActivity().findViewById(R.id.send_action);
    }
}
