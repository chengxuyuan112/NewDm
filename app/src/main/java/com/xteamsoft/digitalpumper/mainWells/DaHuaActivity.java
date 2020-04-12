//package com.xteamsoft.digitalpumper.mainWells;
//
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.res.Configuration;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.os.PersistableBundle;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.widget.Toolbar;
//import android.util.DisplayMetrics;
//import android.util.Log;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.Window;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.ListAdapter;
//import android.widget.ListView;
//import android.widget.RelativeLayout;
//import android.widget.Spinner;
//import android.widget.SpinnerAdapter;
//import android.widget.TextView;
//import android.widget.Toast;
//import com.android.business.adapter.DataAdapteeImpl;
//import com.android.business.adapter.DataAdapterInterface;
//import com.android.business.entity.ChannelInfo;
//import com.android.business.entity.DataInfo;
//import com.android.business.entity.GroupInfo;
//import com.android.business.entity.LogicalInfo;
//import com.android.business.exception.BusinessException;
//import com.android.dahua.playmanager.IMediaPlayListener;
//import com.android.dahua.playmanager.IOperationListener;
//import com.android.dahua.playmanager.IPTZListener;
//import com.android.dahua.playmanager.ITalkListener;
//import com.android.dahua.playmanager.PlayManager;
//import com.example.dhcommonlib.util.MicHelper;
//import com.handmark.pulltorefresh.library.PullToRefreshBase;
//import com.handmark.pulltorefresh.library.PullToRefreshListView;
//import com.mm.Api.Camera;
//import com.mm.Api.DPSRTCamera;
//import com.mm.Api.DPSRTCameraParam;
//import com.mm.audiotalk.target.DPSTalkTarget;
//import com.mm.audiotalk.target.DpsTalk;
//import com.mm.uc.IWindowListener;
//import com.mm.uc.PlayWindow;
//import com.xteamsoft.baselibrary.base.BaseActivity;
//import com.xteamsoft.digitalpumper.R;
//import com.xteamsoft.digitalpumper.adapterWells.PopDaHuaAdapter;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//public class DaHuaActivity extends BaseActivity {
//    public static final int KEY_Handler_First_Frame = 22;
//
//    public static final int KEY_Handler_Net_Error = 33;
//
//    public static final int KEY_Handler_Play_Failed = 44;
//
//    public static final int KEY_Handler_Stream_Played = 11;
//
//    public static final int KEY_Handler_Talk_Success = 77;
//
//    public static final int KEY_Handler_Talk_failed = 88;
//
//    public static final int Stream_Assist_Type = 2;
//
//    public static final int Stream_Main_Type = 1;
//
//    public static final int Stream_Third_Type = 3;
//
//    private static final String TAG = ";
//
//    private ArrayAdapter<String> adapter;
//
//    private ImageView btn_choose;
//
//    private List<ChannelInfo> channelInfoList = new ArrayList<ChannelInfo>();
//
//    protected Map<Integer, ChannelInfo> channelInfoMap = new HashMap<Integer, ChannelInfo>();
//
//    private List<DataInfo> channelInfos = new ArrayList<DataInfo>();
//
//    private Map<String, List<String>> chnIdMap = new HashMap<String, List<String>>();
//
//    private List<DataInfo> chooseinfos = new ArrayList<DataInfo>();
//
//    private PopDaHuaAdapter dahuaAdapter;
//
//    private DataAdapterInterface dataAdapterInterface;
//
//    private Map<String, List<String>> devIdMap = new HashMap<String, List<String>>();
//
//    private GroupInfo groupInfo = null;
//
//    private int hasStartVideo = 0;
//
//    private IMediaPlayListener iMediaPlayListener = new IMediaPlayListener() {
//        public void onPlayeStatusCallback(int param1Int, IMediaPlayListener.PlayStatusType param1PlayStatusType) {
//            Log.d(", "onPlayeStatusCallback:" + param1PlayStatusType + " winIndex: " + param1Int);
//            Message message = Message.obtain();
//            message.obj = Integer.valueOf(param1Int);
//            if (param1PlayStatusType == IMediaPlayListener.PlayStatusType.eStreamPlayed) {
//                message.what = 11;
//                if (DaHuaActivity.this.mDeviceHander != null)
//                    DaHuaActivity.this.mDeviceHander.sendMessage(message);
//                return;
//            }
//            if (param1PlayStatusType == IMediaPlayListener.PlayStatusType.ePlayFirstFrame) {
//                message.what = 22;
//                if (DaHuaActivity.this.mDeviceHander != null) {
//                    DaHuaActivity.this.mDeviceHander.sendMessage(message);
//                    return;
//                }
//                return;
//            }
//            if (param1PlayStatusType == IMediaPlayListener.PlayStatusType.eNetworkaAbort) {
//                message.what = 33;
//                if (DaHuaActivity.this.mDeviceHander != null) {
//                    DaHuaActivity.this.mDeviceHander.sendMessage(message);
//                    return;
//                }
//                return;
//            }
//            if (param1PlayStatusType == IMediaPlayListener.PlayStatusType.ePlayFailed) {
//                message.what = 44;
//                if (DaHuaActivity.this.mDeviceHander != null) {
//                    DaHuaActivity.this.mDeviceHander.sendMessage(message);
//                    return;
//                }
//            }
//        }
//    };
//
//    private IOperationListener iOperationListener = new IOperationListener() {
//        public void onControlClick(int param1Int, IWindowListener.ControlType param1ControlType) {
//            Log.e(", "onControlClick" + param1ControlType);
//            if (param1ControlType != IWindowListener.ControlType.Control_Open && param1ControlType == IWindowListener.ControlType.Control_Reflash) {
//                DaHuaActivity.this.startPlay(param1Int);
//                return;
//            }
//        }
//
//        public void onMoveWindowBegin(int param1Int) {
//            Log.e(", "onMoveWindowBegin");
//        }
//
//        public boolean onMoveWindowEnd(int param1Int, float param1Float1, float param1Float2) {
//            Log.e(", "onMoveWindowEnd x:" + param1Float1 + " y:" + param1Float2);
//            return false;
//        }
//
//        public void onMovingWindow(int param1Int, float param1Float1, float param1Float2) {
//            Log.e(", "onMovingWindow x:" + param1Float1 + " y:" + param1Float2);
//        }
//
//        public void onPageChange(int param1Int1, int param1Int2, int param1Int3) {
//            Log.e(", "onPageChange" + param1Int1 + param1Int2 + param1Int3);
//            if (param1Int3 == 0) {
//                if (DaHuaActivity.this.mPlayManager.getPageCellNumber() == 1) {
//                    DaHuaActivity.this.mPlayManager.setEnableElectronZoom(param1Int2, false);
//                    DaHuaActivity.this.mPlayManager.setEnableElectronZoom(param1Int1, true);
//                }
//                DaHuaActivity.this.refreshBtnState();
//            }
//        }
//
//        public void onSelectWinIndexChange(int param1Int1, int param1Int2) {
//            Log.e(", "onSelectWinIndexChange:" + param1Int1 + ":" + param1Int2);
//            if (!DaHuaActivity.this.mPlayManager.hasTalking()) {
//                if (DaHuaActivity.this.mPlayManager.isOpenAudio(param1Int2)) {
//                    DaHuaActivity.this.mPlayManager.closeAudio(param1Int2);
//                    DaHuaActivity.this.mPlayManager.setNeedOpenAudio(param1Int2, true);
//                }
//                if (DaHuaActivity.this.mPlayManager.isPlaying(param1Int1) && DaHuaActivity.this.mPlayManager.isNeedOpenAudio(param1Int1))
//                    DaHuaActivity.this.mPlayManager.openAudio(param1Int1);
//                DaHuaActivity.this.refreshBtnState();
//            }
//        }
//
//        public void onSplitNumber(int param1Int1, int param1Int2, int param1Int3, int param1Int4) {
//            Log.e(", "onSplitNumber" + param1Int1);
//        }
//
//        public void onWindowDBClick(int param1Int1, int param1Int2) {
//            boolean bool = false;
//            Log.e(", "onWindowDBClick" + param1Int2 + " winIndex:" + param1Int1 + " isWindowMax:" + DaHuaActivity.this.mPlayManager.isWindowMax());
//            if (DaHuaActivity.this.mPlayManager.isOpenPtz(param1Int1) && DaHuaActivity.this.mPlayManager.setEnablePtz(param1Int1, false) == 0)
//                DaHuaActivity.this.mPlayManager.setResumeFlag(param1Int1, false);
//            PlayManager playManager = DaHuaActivity.this.mPlayManager;
//            if (param1Int2 == 0)
//                bool = true;
//            playManager.setEnableElectronZoom(param1Int1, bool);
//        }
//
//        public void onWindowSelected(int param1Int) {
//            Log.e(", "onWindowSelected" + param1Int);
//                    DaHuaActivity.this.refreshBtnState();
//        }
//    };
//
//    private ITalkListener iTalkListener = new ITalkListener() {
//        public void onTalkResult(int param1Int, ITalkListener.TalkResultType param1TalkResultType) {
//            if (param1TalkResultType == ITalkListener.TalkResultType.eTalkSuccess) {
//                if (DaHuaActivity.this.mDeviceHander != null)
//                    DaHuaActivity.this.mDeviceHander.sendEmptyMessage(77);
//                return;
//            }
//            if (param1TalkResultType == ITalkListener.TalkResultType.eTalkFailed && DaHuaActivity.this.mDeviceHander != null) {
//                DaHuaActivity.this.mDeviceHander.sendEmptyMessage(88);
//                return;
//            }
//        }
//    };
//
//    private IPTZListener iptzListener = new IPTZListener() {
//        public void onPTZControl(int param1Int, IPTZListener.PtzOperation param1PtzOperation, boolean param1Boolean1, boolean param1Boolean2) {
//            Log.e(", "onPTZControl oprType:" + param1PtzOperation.toString());
//            DaHuaActivity.this.sendPTZOperation(DaHuaActivity.getPtzOperation(param1PtzOperation), param1Boolean1);
//        }
//
//        public void onPTZZooming(int param1Int, float param1Float, IPTZListener.PtzOperation param1PtzOperation, IPTZListener.PtzZoomState param1PtzZoomState) {
//            String str;
//            if ("onPTZZooming oprType:" + param1PtzOperation == null) {
//                str = "nul";
//            } else if (str.toString() + " state:" + param1PtzZoomState == null) {
//                str = "nul";
//            } else {
//                str = param1PtzZoomState.toString() + " scale:" + param1Float;
//            }
//            Log.e(", str);
//        }
//    };
//
//    private boolean isFirstVisible = true;
//
//    private ListView listView;
//
//    private LinearLayout ll_dahua;
//
//    protected Handler mDeviceHander = new Handler() {
//        public void handleMessage(Message param1Message) {
//            List list;
//            GroupInfo groupInfo;
//            int i;
//            DaHuaActivity.this.mProgressDialog.dismiss();
//            switch (param1Message.what) {
//                default:
//                    return;
//                case 0:
//                    if (this != null) {
//                        list = (List)param1Message.obj;
//                        if (DaHuaActivity.this.dahuaAdapter == null)
//                            DaHuaActivity.this.ChooseDefOne(list);
//                        if (list != null && list.size() > 0)
//                            DaHuaActivity.this.channelInfos.addAll(list);
//                        if (DaHuaActivity.this.dahuaAdapter != null) {
//                            DaHuaActivity.this.dahuaAdapter.appendToList(DaHuaActivity.this.channelInfos);
//                            DaHuaActivity.this.dahuaAdapter.notifyDataSetChanged();
//                            return;
//                        }
//                    }
//                case 1:
//                    if (this != null) {
//                        Toast.makeText((Context)DaHuaActivity.this, ", 1).show();
//                        return;
//                    }
//                case 2:
//                    groupInfo = (GroupInfo)((Message)list).obj;
//                    DaHuaActivity.this.getGroupDeviceList(groupInfo);
//                    return;
//                case 11:
//                    i = ((Integer)((Message)groupInfo).obj).intValue();
//                    Log.e(", ");
//                    if (i == DaHuaActivity.this.mPlayManager.getSelectedWindowIndex()) {
//                        if (DaHuaActivity.this.channelInfoList != null && DaHuaActivity.this.channelInfoList.size() == 1) {
//                            DaHuaActivity.this.mPlayManager.maximizeWindow(i);
//                            DaHuaActivity.this.mPlayManager.setEnableElectronZoom(i, true);
//                        }
//                        if (DaHuaActivity.this.mPlayManager.isNeedOpenAudio(i))
//                            DaHuaActivity.this.openAudio(i);
//                        DaHuaActivity.this.refreshBtnState();
//                        return;
//                    }
//                case 33:
//                    Log.e(", ");
//                case 44:
//                    i = ((Integer)((Message)groupInfo).obj).intValue();
//                    DaHuaActivity.this.stopPlay(i);
//                    return;
//                case 77:
//                    Log.e(", ");
//                    DaHuaActivity.this.tvTalk.setSelected(true);
//                    return;
//                case 88:
//                    break;
//            }
//            DaHuaActivity.this.closeTalk(DaHuaActivity.this.mPlayManager.getSelectedWindowIndex());
//            Log.e(", ");
//            DaHuaActivity.this.tvTalk.setSelected(false);
//        }
//    };
//
//    protected PlayManager mPlayManager;
//
//    private PlayWindow mPlayWin;
//
//    private ProgressDialog mProgressDialog;
//
//    protected String[] recordPath;
//
//    private Spinner spinner;
//
//    private Toolbar toolbar;
//
//    private TextView toolbar_title;
//
//    private TextView tvSound;
//
//    private TextView tvStreamAssist;
//
//    private TextView tvStreamMain;
//
//    private TextView tvStreamThird;
//
//    private TextView tvTalk;
//
//    private TextView tv_dahua;
//
//    private ImageButton video_bottom;
//
//    private ImageButton video_bottomLeft;
//
//    private ImageButton video_bottomRight;
//
//    private ImageButton video_left;
//
//    private ImageButton video_right;
//
//    private ImageButton video_top;
//
//    private ImageButton video_topLeft;
//
//    private ImageButton video_topRight;
//
//    private void ChooseDefOne(List<DataInfo> paramList) {
//        ChannelInfo channelInfo;
//        Log.e(", "" + paramList.toArray().toString());
//                DataInfo dataInfo = paramList.get(0);
//        paramList = null;
//        if (dataInfo instanceof ChannelInfo) {
//            channelInfo = (ChannelInfo)dataInfo;
//        } else if (dataInfo instanceof LogicalInfo) {
//            channelInfo = (ChannelInfo)((LogicalInfo)dataInfo).getDataInfo();
//        }
//        if (channelInfo.getState() == ChannelInfo.ChannelState.Online) {
//            this.channelInfoList.add(channelInfo);
//            this.ll_dahua.setVisibility(0);
//            this.tv_dahua.setText(channelInfo.getName());
//            Log.e(", ");
//            startThisPlay();
//            return;
//        }
//        Toast.makeText((Context)this, ", 1).show();
//    }
//
//    private void ChooseDefother(List<DataInfo> paramList, int paramInt) {
//        ChannelInfo channelInfo;
//        this.channelInfoList.clear();
//        Log.e(", "" + paramList.toArray().toString());
//                DataInfo dataInfo = paramList.get(paramInt);
//        paramList = null;
//        if (dataInfo instanceof ChannelInfo) {
//            channelInfo = (ChannelInfo)dataInfo;
//        } else if (dataInfo instanceof LogicalInfo) {
//            channelInfo = (ChannelInfo)((LogicalInfo)dataInfo).getDataInfo();
//        }
//        if (channelInfo.getState() == ChannelInfo.ChannelState.Online) {
//            this.channelInfoList.add(channelInfo);
//            this.ll_dahua.setVisibility(0);
//            this.tv_dahua.setText(channelInfo.getName());
//            Log.e(", ");
//            startThisPlay();
//            return;
//        }
//        Toast.makeText((Context)this, ", 1).show();
//    }
//
//    private void changeModeStream(int paramInt) {
//        // Byte code:
//        //   0: aload_0
//        //   1: getfield mPlayManager : Lcom/android/dahua/playmanager/PlayManager;
//        //   4: aload_0
//        //   5: getfield mPlayManager : Lcom/android/dahua/playmanager/PlayManager;
//        //   8: invokevirtual getSelectedWindowIndex : ()I
//        //   11: invokevirtual isOpenPtz : (I)Z
//        //   14: ifeq -> 17
//        //   17: aload_0
//        //   18: getfield mPlayManager : Lcom/android/dahua/playmanager/PlayManager;
//        //   21: invokevirtual hasRecording : ()Z
//        //   24: ifeq -> 27
//        //   27: aload_0
//        //   28: getfield mPlayManager : Lcom/android/dahua/playmanager/PlayManager;
//        //   31: invokevirtual getSelectedWindowIndex : ()I
//        //   34: istore_2
//        //   35: aload_0
//        //   36: getfield mPlayManager : Lcom/android/dahua/playmanager/PlayManager;
//        //   39: iload_2
//        //   40: invokevirtual stop : (I)I
//        //   43: pop
//        //   44: aload_0
//        //   45: getfield mPlayManager : Lcom/android/dahua/playmanager/PlayManager;
//        //   48: iload_2
//        //   49: invokevirtual getWindowChannelInfo : (I)Lcom/android/dahua/playmanager/inner/WindowChannelInfo;
//        //   52: invokevirtual getCameraParam : ()Lcom/mm/Api/Camera;
//        //   55: checkcast com/mm/Api/DPSRTCamera
//        //   58: astore_3
//        //   59: new com/mm/Api/DPSRTCameraParam
//        //   62: dup
//        //   63: invokespecial <init> : ()V
//        //   66: astore #4
//        //   68: aload #4
//        //   70: aload_3
//        //   71: invokevirtual getCameraParam : ()Lcom/mm/Api/inner/BaseInnerParam;
//        //   74: invokevirtual getCameraID : ()Ljava/lang/String;
//        //   77: invokevirtual setCameraID : (Ljava/lang/String;)V
//        //   80: aload #4
//        //   82: aload_0
//        //   83: getfield dataAdapterInterface : Lcom/android/business/adapter/DataAdapterInterface;
//        //   86: invokeinterface getDPSDKEntityHandle : ()J
//        //   91: invokestatic valueOf : (J)Ljava/lang/String;
//        //   94: invokevirtual setDpHandle : (Ljava/lang/String;)V
//        //   97: aload #4
//        //   99: iload_1
//        //   100: invokevirtual setStreamType : (I)V
//        //   103: aload #4
//        //   105: aload_3
//        //   106: invokevirtual getCameraParam : ()Lcom/mm/Api/inner/BaseInnerParam;
//        //   109: invokevirtual getMediaType : ()I
//        //   112: invokevirtual setMediaType : (I)V
//        //   115: aload #4
//        //   117: iconst_1
//        //   118: invokevirtual setCheckPermission : (Z)V
//        //   121: aload #4
//        //   123: iconst_m1
//        //   124: invokevirtual setStartChannleIndex : (I)V
//        //   127: aload #4
//        //   129: iconst_0
//        //   130: invokevirtual setSeparateNum : (I)V
//        //   133: aload #4
//        //   135: ldc_w '601'
//        //   138: invokevirtual setTrackID : (Ljava/lang/String;)V
//        //   141: new com/mm/Api/DPSRTCamera
//        //   144: dup
//        //   145: aload #4
//        //   147: invokespecial <init> : (Lcom/mm/Api/DPSRTCameraParam;)V
//        //   150: astore_3
//        //   151: aload_0
//        //   152: getfield mPlayManager : Lcom/android/dahua/playmanager/PlayManager;
//        //   155: iload_2
//        //   156: aload_3
//        //   157: invokevirtual playSingle : (ILcom/mm/Api/Camera;)I
//        //   160: pop
//        //   161: return
//        //   162: astore #5
//        //   164: aload #5
//        //   166: invokevirtual printStackTrace : ()V
//        //   169: goto -> 97
//        // Exception table:
//        //   from	to	target	type
//        //   80	97	162	com/android/business/exception/BusinessException
//    }
//
//    private void chooseDialog() {
//        final AlertDialog alertDialog = (new AlertDialog.Builder((Context)this, 2131493126)).create();
//        alertDialog.show();
//        alertDialog.setContentView(2130968639);
//        Window window = alertDialog.getWindow();
//        window.setLayout(-1, -2);
//        window.setGravity(80);
//        window.setWindowAnimations(2131493067);
//        PullToRefreshListView pullToRefreshListView = (PullToRefreshListView)window.findViewById(2131886471);
//        this.listView = (ListView)pullToRefreshListView.getRefreshableView();
//        pullToRefreshListView.setMode(PullToRefreshBase.Mode.DISABLED);
//        this.dahuaAdapter = new PopDaHuaAdapter((Context)this);
//        this.listView.setAdapter((ListAdapter)this.dahuaAdapter);
//        getGroupList();
//        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
//                Log.e("position", param1Int + "");
//                DaHuaActivity.this.ChooseDefother(DaHuaActivity.this.channelInfos, param1Int - 1);
//                alertDialog.dismiss();
//            }
//        });
//        window.findViewById(2131886469).setOnClickListener(new View.OnClickListener() {
//            public void onClick(View param1View) {
//                alertDialog.dismiss();
//            }
//        });
//    }
//
//    private void closeTalk(int paramInt) {
//        if (this.mPlayManager.stopTalk(paramInt) == 0) {
//            Log.e(", ");
//            this.tvTalk.setSelected(false);
//        }
//    }
//
//    private DPSRTCamera getCamera(ChannelInfo paramChannelInfo) {
//        DPSRTCameraParam dPSRTCameraParam = new DPSRTCameraParam();
//        dPSRTCameraParam.setCameraID(paramChannelInfo.getChnSncode());
//        try {
//            dPSRTCameraParam.setDpHandle(String.valueOf(this.dataAdapterInterface.getDPSDKEntityHandle()));
//        } catch (BusinessException businessException) {
//            businessException.printStackTrace();
//        }
//        int j = ChannelInfo.ChannelStreamType.getValue(paramChannelInfo.getStreamType());
//        int i = j;
//        if (j > 2)
//            i = 2;
//        dPSRTCameraParam.setStreamType(i);
//        dPSRTCameraParam.setMediaType(3);
//        dPSRTCameraParam.setCheckPermission(true);
//        dPSRTCameraParam.setStartChannleIndex(-1);
//        dPSRTCameraParam.setSeparateNum(0);
//        dPSRTCameraParam.setTrackID("601");
//        return new DPSRTCamera(dPSRTCameraParam);
//    }
//
//    private List<Camera> getCameras() {
//        ArrayList<DPSRTCamera> arrayList = new ArrayList();
//        Iterator<ChannelInfo> iterator = this.channelInfoList.iterator();
//        while (iterator.hasNext())
//            arrayList.add(getCamera(iterator.next()));
//        return (List)arrayList;
//    }
//
//    private void getGroupDeviceList(final GroupInfo groupInfo) {
//        this.groupInfo = groupInfo;
//        this.channelInfos.clear();
//        this.mProgressDialog = ProgressDialog.show((Context)this, null, getString(2131296434));
//        this.mProgressDialog.setCanceledOnTouchOutside(true);
//        (new Thread(new Runnable() {
//            public void run() {
//                // Byte code:
//                //   0: aconst_null
//                //   1: astore_1
//                //   2: aload_0
//                //   3: getfield this$0 : Lcom/xteamsoft/digitalpumper/mainWells/DaHuaActivity;
//                //   6: invokestatic access$1500 : (Lcom/xteamsoft/digitalpumper/mainWells/DaHuaActivity;)Ljava/util/Map;
//                //   9: aload_0
//                //   10: getfield val$groupInfo : Lcom/android/business/entity/GroupInfo;
//                //   13: invokevirtual getGroupId : ()Ljava/lang/String;
//                //   16: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
//                //   21: checkcast java/util/List
//                //   24: astore_2
//                //   25: aload_2
//                //   26: ifnull -> 169
//                //   29: aload_0
//                //   30: getfield this$0 : Lcom/xteamsoft/digitalpumper/mainWells/DaHuaActivity;
//                //   33: invokestatic access$1000 : (Lcom/xteamsoft/digitalpumper/mainWells/DaHuaActivity;)Lcom/android/business/adapter/DataAdapterInterface;
//                //   36: aload_2
//                //   37: invokeinterface getDeviceList : (Ljava/util/List;)Lcom/android/business/adapter/DeviceWithChannelList;
//                //   42: astore_2
//                //   43: aload_2
//                //   44: astore_1
//                //   45: aload_1
//                //   46: ifnull -> 254
//                //   49: aload_1
//                //   50: invokevirtual getDevWithChannelList : ()Ljava/util/List;
//                //   53: ifnull -> 254
//                //   56: aload_1
//                //   57: invokevirtual getDevWithChannelList : ()Ljava/util/List;
//                //   60: invokeinterface iterator : ()Ljava/util/Iterator;
//                //   65: astore_1
//                //   66: aload_1
//                //   67: invokeinterface hasNext : ()Z
//                //   72: ifeq -> 217
//                //   75: aload_1
//                //   76: invokeinterface next : ()Ljava/lang/Object;
//                //   81: checkcast com/android/business/adapter/DeviceWithChannelListBean
//                //   84: astore_3
//                //   85: aload_3
//                //   86: invokevirtual getDeviceInfo : ()Lcom/android/business/entity/DeviceInfo;
//                //   89: astore_2
//                //   90: aload_3
//                //   91: invokevirtual getChannelList : ()Ljava/util/List;
//                //   94: invokeinterface iterator : ()Ljava/util/Iterator;
//                //   99: astore_3
//                //   100: aload_3
//                //   101: invokeinterface hasNext : ()Z
//                //   106: ifeq -> 66
//                //   109: aload_3
//                //   110: invokeinterface next : ()Ljava/lang/Object;
//                //   115: checkcast com/android/business/entity/DataInfo
//                //   118: checkcast com/android/business/entity/ChannelInfo
//                //   121: astore #4
//                //   123: aload #4
//                //   125: aload_2
//                //   126: invokevirtual getSnCode : ()Ljava/lang/String;
//                //   129: invokevirtual setDeviceUuid : (Ljava/lang/String;)V
//                //   132: aload #4
//                //   134: invokevirtual getCategory : ()Lcom/android/business/entity/ChannelInfo$ChannelCategory;
//                //   137: getstatic com/android/business/entity/ChannelInfo$ChannelCategory.videoInputChannel : Lcom/android/business/entity/ChannelInfo$ChannelCategory;
//                //   140: if_acmpne -> 100
//                //   143: aload_0
//                //   144: getfield this$0 : Lcom/xteamsoft/digitalpumper/mainWells/DaHuaActivity;
//                //   147: invokestatic access$300 : (Lcom/xteamsoft/digitalpumper/mainWells/DaHuaActivity;)Ljava/util/List;
//                //   150: aload #4
//                //   152: invokeinterface add : (Ljava/lang/Object;)Z
//                //   157: pop
//                //   158: goto -> 100
//                //   161: astore_2
//                //   162: aload_2
//                //   163: invokevirtual printStackTrace : ()V
//                //   166: goto -> 45
//                //   169: aload_0
//                //   170: getfield this$0 : Lcom/xteamsoft/digitalpumper/mainWells/DaHuaActivity;
//                //   173: invokestatic access$1000 : (Lcom/xteamsoft/digitalpumper/mainWells/DaHuaActivity;)Lcom/android/business/adapter/DataAdapterInterface;
//                //   176: aload_0
//                //   177: getfield this$0 : Lcom/xteamsoft/digitalpumper/mainWells/DaHuaActivity;
//                //   180: invokestatic access$1600 : (Lcom/xteamsoft/digitalpumper/mainWells/DaHuaActivity;)Ljava/util/Map;
//                //   183: aload_0
//                //   184: getfield val$groupInfo : Lcom/android/business/entity/GroupInfo;
//                //   187: invokevirtual getGroupId : ()Ljava/lang/String;
//                //   190: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
//                //   195: checkcast java/util/List
//                //   198: invokeinterface getDeviceListByChannelList : (Ljava/util/List;)Lcom/android/business/adapter/DeviceWithChannelList;
//                //   203: astore_2
//                //   204: aload_2
//                //   205: astore_1
//                //   206: goto -> 45
//                //   209: astore_2
//                //   210: aload_2
//                //   211: invokevirtual printStackTrace : ()V
//                //   214: goto -> 45
//                //   217: new android/os/Message
//                //   220: dup
//                //   221: invokespecial <init> : ()V
//                //   224: astore_1
//                //   225: aload_1
//                //   226: iconst_0
//                //   227: putfield what : I
//                //   230: aload_1
//                //   231: aload_0
//                //   232: getfield this$0 : Lcom/xteamsoft/digitalpumper/mainWells/DaHuaActivity;
//                //   235: invokestatic access$300 : (Lcom/xteamsoft/digitalpumper/mainWells/DaHuaActivity;)Ljava/util/List;
//                //   238: putfield obj : Ljava/lang/Object;
//                //   241: aload_0
//                //   242: getfield this$0 : Lcom/xteamsoft/digitalpumper/mainWells/DaHuaActivity;
//                //   245: getfield mDeviceHander : Landroid/os/Handler;
//                //   248: aload_1
//                //   249: invokevirtual sendMessage : (Landroid/os/Message;)Z
//                //   252: pop
//                //   253: return
//                //   254: aload_0
//                //   255: getfield this$0 : Lcom/xteamsoft/digitalpumper/mainWells/DaHuaActivity;
//                //   258: getfield mDeviceHander : Landroid/os/Handler;
//                //   261: iconst_1
//                //   262: invokevirtual sendEmptyMessage : (I)Z
//                //   265: pop
//                //   266: return
//                // Exception table:
//                //   from	to	target	type
//                //   29	43	161	com/android/business/exception/BusinessException
//                //   169	204	209	com/android/business/exception/BusinessException
//            }
//        })).start();
//    }
//
//    private void getGroupList() {
//        this.mProgressDialog = ProgressDialog.show((Context)this, null, getString(2131296434));
//        this.mProgressDialog.setCanceledOnTouchOutside(true);
//        (new Thread(new Runnable() {
//            public void run() {
//                try {
//                    GroupInfo groupInfo = DaHuaActivity.this.dataAdapterInterface.queryGroupInfo(null);
//                    for (GroupInfo groupInfo1 : DaHuaActivity.this.dataAdapterInterface.queryGroup(groupInfo.getGroupId())) {
//                        if (groupInfo1.getDevList() != null)
//                            DaHuaActivity.this.devIdMap.put(groupInfo1.getGroupId(), groupInfo1.getDevList());
//                        if (groupInfo1.getChannelList() != null)
//                            DaHuaActivity.this.chnIdMap.put(groupInfo1.getGroupId(), groupInfo1.getChannelList());
//                    }
//                } catch (BusinessException businessException) {
//                    businessException.printStackTrace();
//                    return;
//                }
//                Message message = new Message();
//                message.what = 2;
//                message.obj = businessException;
//                DaHuaActivity.this.mDeviceHander.sendMessage(message);
//            }
//        })).start();
//    }
//
//    public static ChannelInfo.PtzOperation getPtzOperation(IPTZListener.PtzOperation paramPtzOperation) {
//        ChannelInfo.PtzOperation ptzOperation = ChannelInfo.PtzOperation.stop;
//        if (paramPtzOperation == IPTZListener.PtzOperation.up)
//            ptzOperation = ChannelInfo.PtzOperation.up;
//        if (paramPtzOperation == IPTZListener.PtzOperation.down)
//            ptzOperation = ChannelInfo.PtzOperation.down;
//        if (paramPtzOperation == IPTZListener.PtzOperation.left)
//            ptzOperation = ChannelInfo.PtzOperation.left;
//        if (paramPtzOperation == IPTZListener.PtzOperation.right)
//            ptzOperation = ChannelInfo.PtzOperation.right;
//        if (paramPtzOperation == IPTZListener.PtzOperation.leftUp)
//            ptzOperation = ChannelInfo.PtzOperation.leftUp;
//        if (paramPtzOperation == IPTZListener.PtzOperation.leftDown)
//            ptzOperation = ChannelInfo.PtzOperation.leftDown;
//        if (paramPtzOperation == IPTZListener.PtzOperation.rightUp)
//            ptzOperation = ChannelInfo.PtzOperation.rightUp;
//        if (paramPtzOperation == IPTZListener.PtzOperation.rightDown)
//            ptzOperation = ChannelInfo.PtzOperation.rightDown;
//        return ptzOperation;
//    }
//
//    private void initCtrl() {
//        this.toolbar.setNavigationIcon(2130903169);
//        setSupportActionBar(this.toolbar);
//        this.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            public void onClick(View param1View) {
//                (new Handler()).post(new Runnable() {
//                    public void run() {
//                        try {
//                            DaHuaActivity.this.dataAdapterInterface.logout();
//                            return;
//                        } catch (BusinessException businessException) {
//                            businessException.printStackTrace();
//                            return;
//                        }
//                    }
//                });
//                DaHuaActivity.this.finish();
//            }
//        });
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        this.video_top.setOnLongClickListener(new View.OnLongClickListener() {
//            public boolean onLongClick(View param1View) {
//                return false;
//            }
//        });
//        this.tvTalk.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View param1View) {
//                if (DaHuaActivity.this.mPlayManager != null)
//                    DaHuaActivity.this.onClickTalk();
//            }
//        });
//        this.btn_choose.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View param1View) {
//                DaHuaActivity.this.chooseDialog();
//            }
//        });
//        this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            public void onItemSelected(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
//                switch (param1Int) {
//                    default:
//                        return;
//                    case 0:
//                        if (DaHuaActivity.this.mPlayManager != null)
//                            DaHuaActivity.this.changeModeStream(1);
//                        Log.e("spinner", param1Int + ");
//                        return;
//                    case 1:
//                        if (DaHuaActivity.this.mPlayManager != null) {
//                            DaHuaActivity.this.changeModeStream(2);
//                            Log.e("spinner", param1Int + ");
//                            return;
//                        }
//                    case 2:
//                        break;
//                }
//                if (DaHuaActivity.this.mPlayManager != null) {
//                    DaHuaActivity.this.changeModeStream(3);
//                    Log.e("spinner", param1Int + ");
//                    return;
//                }
//            }
//
//            public void onNothingSelected(AdapterView<?> param1AdapterView) {}
//        });
//    }
//
//    private void initData() {
//        this.dataAdapterInterface = DataAdapteeImpl.getInstance();
//        if (this.isFirstVisible) {
//            this.isFirstVisible = false;
//            getGroupList();
//        }
//    }
//
//    private void initView() {
//        this.toolbar_title = (TextView)findViewById(2131886762);
//        this.toolbar = (Toolbar)findViewById(2131886763);
//        this.toolbar_title.setVisibility(4);
//        this.mPlayWin = (PlayWindow)findViewById(2131886365);
//        this.tvStreamMain = (TextView)findViewById(2131886369);
//        this.tvStreamAssist = (TextView)findViewById(2131886370);
//        this.tvStreamThird = (TextView)findViewById(2131886371);
//        CompentOnTouch compentOnTouch = new CompentOnTouch();
//        this.video_top = (ImageButton)findViewById(2131886373);
//        this.video_right = (ImageButton)findViewById(2131886376);
//        this.video_bottom = (ImageButton)findViewById(2131886377);
//        this.video_left = (ImageButton)findViewById(2131886380);
//        this.video_topLeft = (ImageButton)findViewById(2131886374);
//        this.video_topRight = (ImageButton)findViewById(2131886375);
//        this.video_bottomLeft = (ImageButton)findViewById(2131886378);
//        this.video_bottomRight = (ImageButton)findViewById(2131886379);
//        this.video_top.setOnTouchListener(compentOnTouch);
//        this.video_right.setOnTouchListener(compentOnTouch);
//        this.video_bottom.setOnTouchListener(compentOnTouch);
//        this.video_left.setOnTouchListener(compentOnTouch);
//        this.video_topLeft.setOnTouchListener(compentOnTouch);
//        this.video_topRight.setOnTouchListener(compentOnTouch);
//        this.video_bottomLeft.setOnTouchListener(compentOnTouch);
//        this.video_bottomRight.setOnTouchListener(compentOnTouch);
//        this.ll_dahua = (LinearLayout)findViewById(2131886366);
//        this.tv_dahua = (TextView)findViewById(2131886368);
//        this.tvTalk = (TextView)findViewById(2131886381);
//        this.tvSound = (TextView)findViewById(2131886382);
//        this.btn_choose = (ImageView)findViewById(2131886362);
//        this.spinner = (Spinner)findViewById(2131886363);
//        this.adapter = new ArrayAdapter((Context)this, 2130968733, 2131886119);
//        this.adapter.setDropDownViewResource(2130968732);
//        this.adapter.add("HD");
//        this.adapter.add("SD");
//        this.adapter.add("Normal");
//        this.spinner.setAdapter((SpinnerAdapter)this.adapter);
//        this.spinner.setSelection(1, true);
//    }
//
//    private void onClickSound() {
//        int i = this.mPlayManager.getSelectedWindowIndex();
//        if (this.mPlayManager.isPlaying(i)) {
//            this.tvTalk.setSelected(false);
//            if (this.mPlayManager.isOpenAudio(i) && closeAudio(i)) {
//                this.tvSound.setSelected(false);
//                return;
//            }
//            if (this.mPlayManager.hasTalking())
//                Log.e(", ");
//            if (openAudio(i)) {
//                this.tvSound.setSelected(true);
//                return;
//            }
//        }
//    }
//
//    private void onClickTalk() {
//        int i = this.mPlayManager.getSelectedWindowIndex();
//        if (!this.mPlayManager.isPlaying(i))
//            return;
//        if (this.mPlayManager.isTalking(i)) {
//            closeTalk(i);
//            return;
//        }
//        if (!MicHelper.isVoicePermission()) {
//            Log.e(", ");
//            Toast.makeText((Context)this, ", 1).show();
//            return;
//        }
//        openTalk(i);
//    }
//
//    private void onTouchChange(String paramString, int paramInt) {
//        if (this.mPlayManager != null) {
//            if ("top".equals(paramString)) {
//                Log.e(", ");
//                if (paramInt == 0) {
//                    sendPTZOperation(getPtzOperation(IPTZListener.PtzOperation.up), false);
//                    return;
//                }
//                if (paramInt == 1) {
//                    sendPTZOperation(getPtzOperation(IPTZListener.PtzOperation.up), true);
//                    return;
//                }
//                if (paramInt == 2) {
//                    sendPTZOperation(getPtzOperation(IPTZListener.PtzOperation.up), true);
//                    return;
//                }
//                return;
//            }
//        } else {
//            return;
//        }
//        if ("right".equals(paramString)) {
//            Log.e(", ");
//            if (paramInt == 0) {
//                sendPTZOperation(getPtzOperation(IPTZListener.PtzOperation.right), false);
//                return;
//            }
//            if (paramInt == 1) {
//                sendPTZOperation(getPtzOperation(IPTZListener.PtzOperation.right), true);
//                return;
//            }
//            if (paramInt == 2) {
//                sendPTZOperation(getPtzOperation(IPTZListener.PtzOperation.right), true);
//                return;
//            }
//            return;
//        }
//        if ("bottom".equals(paramString)) {
//            Log.e(", ");
//            if (paramInt == 0) {
//                sendPTZOperation(getPtzOperation(IPTZListener.PtzOperation.down), false);
//                return;
//            }
//            if (paramInt == 1) {
//                sendPTZOperation(getPtzOperation(IPTZListener.PtzOperation.down), true);
//                return;
//            }
//            if (paramInt == 2) {
//                sendPTZOperation(getPtzOperation(IPTZListener.PtzOperation.down), true);
//                return;
//            }
//            return;
//        }
//        if ("left".equals(paramString)) {
//            Log.e(", ");
//            if (paramInt == 0) {
//                sendPTZOperation(getPtzOperation(IPTZListener.PtzOperation.left), false);
//                return;
//            }
//            if (paramInt == 1) {
//                sendPTZOperation(getPtzOperation(IPTZListener.PtzOperation.left), true);
//                return;
//            }
//            if (paramInt == 2) {
//                sendPTZOperation(getPtzOperation(IPTZListener.PtzOperation.left), true);
//                return;
//            }
//            return;
//        }
//        if ("leftUp".equals(paramString)) {
//            Log.e(", ");
//            if (paramInt == 0) {
//                sendPTZOperation(getPtzOperation(IPTZListener.PtzOperation.leftUp), false);
//                return;
//            }
//            if (paramInt == 1) {
//                sendPTZOperation(getPtzOperation(IPTZListener.PtzOperation.leftUp), true);
//                return;
//            }
//            if (paramInt == 2) {
//                sendPTZOperation(getPtzOperation(IPTZListener.PtzOperation.leftUp), true);
//                return;
//            }
//            return;
//        }
//        if ("rightUp".equals(paramString)) {
//            Log.e(", ");
//            if (paramInt == 0) {
//                sendPTZOperation(getPtzOperation(IPTZListener.PtzOperation.rightUp), false);
//                return;
//            }
//            if (paramInt == 1) {
//                sendPTZOperation(getPtzOperation(IPTZListener.PtzOperation.rightUp), true);
//                return;
//            }
//            if (paramInt == 2) {
//                sendPTZOperation(getPtzOperation(IPTZListener.PtzOperation.rightUp), true);
//                return;
//            }
//            return;
//        }
//        if ("leftDown".equals(paramString)) {
//            Log.e(", ");
//            if (paramInt == 0) {
//                sendPTZOperation(getPtzOperation(IPTZListener.PtzOperation.leftDown), false);
//                return;
//            }
//            if (paramInt == 1) {
//                sendPTZOperation(getPtzOperation(IPTZListener.PtzOperation.leftDown), true);
//                return;
//            }
//            if (paramInt == 2) {
//                sendPTZOperation(getPtzOperation(IPTZListener.PtzOperation.leftDown), true);
//                return;
//            }
//            return;
//        }
//        if ("rightDown".equals(paramString)) {
//            Log.e(", ");
//            if (paramInt == 0) {
//                sendPTZOperation(getPtzOperation(IPTZListener.PtzOperation.rightDown), false);
//                return;
//            }
//            if (paramInt == 1) {
//                sendPTZOperation(getPtzOperation(IPTZListener.PtzOperation.rightDown), true);
//                return;
//            }
//            if (paramInt == 2) {
//                sendPTZOperation(getPtzOperation(IPTZListener.PtzOperation.rightDown), true);
//                return;
//            }
//        }
//    }
//
//    private void openTalk(int paramInt) {
//        if (this.mPlayManager.isPlayed(this.mPlayManager.getSelectedWindowIndex()) && this.channelInfoMap.containsKey(Integer.valueOf(paramInt)))
//            try {
//                DpsTalk dpsTalk = new DpsTalk();
//                dpsTalk.setCameraID(((ChannelInfo)this.channelInfoMap.get(Integer.valueOf(this.mPlayManager.getSelectedWindowIndex()))).getDeviceUuid());
//                dpsTalk.setDpHandle(String.valueOf(this.dataAdapterInterface.getDPSDKEntityHandle()));
//                dpsTalk.setTalkType(1);
//                dpsTalk.setTransMode(1);
//                dpsTalk.setSampleRate(8000);
//                dpsTalk.setSampleDepth(16);
//                dpsTalk.setEncodeType(14);
//                DPSTalkTarget dPSTalkTarget = new DPSTalkTarget(dpsTalk);
//                this.mPlayManager.startTalk(paramInt, dPSTalkTarget);
//                return;
//            } catch (BusinessException businessException) {
//                businessException.printStackTrace();
//                return;
//            }
//    }
//
//    private void playBatch() {
//        this.mPlayManager.playBatch(getCameras());
//        this.hasStartVideo++;
//    }
//
//    private void refreshBtnState() {
//        int i = this.mPlayManager.getSelectedWindowIndex();
//        this.tvTalk.setSelected(this.mPlayManager.isTalking(i));
//        this.tvSound.setSelected(this.mPlayManager.isOpenAudio(i));
//        if (this.channelInfoMap.containsKey(Integer.valueOf(i))) {
//            setSupportStreamTag(ChannelInfo.ChannelStreamType.getValue(((ChannelInfo)this.channelInfoMap.get(Integer.valueOf(i))).getStreamType()));
//        } else {
//            setSupportStreamTag(-1);
//        }
//        if (this.mPlayManager.getWindowChannelInfo(i) == null || this.mPlayManager.getWindowChannelInfo(i).getCameraParam() == null || ((DPSRTCamera)this.mPlayManager.getWindowChannelInfo(i).getCameraParam()).getCameraParam() == null) {
//            this.tvStreamMain.setSelected(false);
//            this.tvStreamAssist.setSelected(false);
//            this.tvStreamThird.setSelected(false);
//            return;
//        }
//        switch (((DPSRTCamera)this.mPlayManager.getWindowChannelInfo(i).getCameraParam()).getCameraParam().getStreamType()) {
//            default:
//                return;
//            case 1:
//                this.tvStreamMain.setSelected(true);
//                this.tvStreamAssist.setSelected(false);
//                this.tvStreamThird.setSelected(false);
//                return;
//            case 2:
//                this.tvStreamMain.setSelected(false);
//                this.tvStreamAssist.setSelected(true);
//                this.tvStreamThird.setSelected(false);
//                return;
//            case 3:
//                break;
//        }
//        this.tvStreamMain.setSelected(false);
//        this.tvStreamAssist.setSelected(false);
//        this.tvStreamThird.setSelected(true);
//    }
//
//    private void replay() {
//        this.mPlayManager.replay();
//    }
//
//    private void sendPTZOperation(final ChannelInfo.PtzOperation operation, final boolean isStop) {
//        if (operation == null)
//            return;
//        this.mDeviceHander.post(new Runnable() {
//            public void run() {
//                try {
//                    DaHuaActivity.this.dataAdapterInterface.operatePTZ(operation, ((ChannelInfo)DaHuaActivity.this.channelInfoList.get(DaHuaActivity.this.mPlayManager.getSelectedWindowIndex())).getUuid(), 4, isStop);
//                    return;
//                } catch (BusinessException businessException) {
//                    businessException.printStackTrace();
//                    return;
//                }
//            }
//        });
//    }
//
//    private void startPlay(int paramInt) {
//        this.mPlayManager.play(paramInt);
//    }
//
//    private void startThisPlay() {
//        this.dataAdapterInterface = DataAdapteeImpl.getInstance();
//        this.mPlayManager = new PlayManager();
//        this.mPlayManager.init((Context)this, 1, 1, this.mPlayWin);
//        this.mPlayManager.setOnTalkListener(this.iTalkListener);
//        this.mPlayManager.setOnMediaPlayListener(this.iMediaPlayListener);
//        this.mPlayManager.setOnPTZListener(this.iptzListener);
//        this.mPlayManager.setOnOperationListener(this.iOperationListener);
//        initCommonWindow();
//        int i = 0;
//        for (ChannelInfo channelInfo : this.channelInfoList) {
//            this.channelInfoMap.put(Integer.valueOf(i), channelInfo);
//            i++;
//        }
//        playBatch();
//    }
//
//    private void stopAll() {
//        if (this.mPlayManager != null)
//            this.mPlayManager.stopAll(true);
//    }
//
//    private void stopPlay(int paramInt) {
//        this.mPlayManager.stop(paramInt);
//        Log.e(", ");
//    }
//
//    public boolean closeAudio(int paramInt) {
//        return (this.mPlayManager.closeAudio(paramInt) == 0);
//    }
//
//    public void initCommonWindow() {
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        int i = displayMetrics.widthPixels;
//        int j = displayMetrics.heightPixels;
//        j = i * 3 / 4;
//        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)this.mPlayWin.getLayoutParams();
//        layoutParams.width = i;
//        layoutParams.height = j;
//        this.mPlayWin.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
//        this.mPlayWin.forceLayout(i, j);
//    }
//
//    public void onCompleted(int paramInt) {}
//
//    public void onConfigurationChanged(Configuration paramConfiguration) {
//        super.onConfigurationChanged(paramConfiguration);
//        if ((getResources().getConfiguration()).orientation == 2) {
//            Log.e(", ");
//        } else if ((getResources().getConfiguration()).orientation == 1) {
//            Log.e(", ");
//        }
//        if (paramConfiguration.hardKeyboardHidden != 1 && paramConfiguration.hardKeyboardHidden == 2)
//            return;
//    }
//
//    protected void onCreate(Bundle paramBundle) {
//        super.onCreate(paramBundle);
//        setContentView(R.layout.activity_da_hua);
//        if (Build.VERSION.SDK_INT > 18) {
//            int i = 0;
//            int j = getResources().getIdentifier("status_bar_height", "dimen", "android");
//            if (j > 0)
//                i = getResources().getDimensionPixelSize(j);
//            ((LinearLayout)findViewById(2131886761)).setLayoutParams((ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(getWindowManager().getDefaultDisplay().getWidth(), i));
//        }
//        initView();
//        initData();
//        initCtrl();
//    }
//
//    protected void onDestroy() {
//        super.onDestroy();
//        if (this.mPlayManager != null) {
//            this.mPlayManager.uninit();
//            this.mPlayManager = null;
//        }
//    }
//
//    public void onError(Throwable paramThrowable, int paramInt) {}
//
//    public void onNext(Object paramObject, int paramInt) {}
//
//    protected void onPause() {
//        super.onPause();
//        stopAll();
//    }
//
//    protected void onRestoreInstanceState(Bundle paramBundle) {
//        super.onRestoreInstanceState(paramBundle);
//    }
//
//    protected void onResume() {
//        super.onResume();
//        if (this.hasStartVideo > 1)
//            replay();
//    }
//
//    public void onSaveInstanceState(Bundle paramBundle, PersistableBundle paramPersistableBundle) {
//        super.onSaveInstanceState(paramBundle, paramPersistableBundle);
//    }
//
//    public boolean openAudio(int paramInt) {
//        return (this.mPlayManager.openAudio(paramInt) == 0);
//    }
//
//    public void setSupportStreamTag(int paramInt) {
//        switch (paramInt) {
//            default:
//                this.tvStreamMain.setEnabled(false);
//                this.tvStreamAssist.setEnabled(false);
//                this.tvStreamThird.setEnabled(false);
//                return;
//            case 1:
//                this.tvStreamMain.setEnabled(true);
//                this.tvStreamAssist.setEnabled(false);
//                this.tvStreamThird.setEnabled(false);
//                return;
//            case 2:
//                this.tvStreamMain.setEnabled(true);
//                this.tvStreamAssist.setEnabled(true);
//                this.tvStreamThird.setEnabled(false);
//                return;
//            case 3:
//                break;
//        }
//        this.tvStreamMain.setEnabled(true);
//        this.tvStreamAssist.setEnabled(true);
//        this.tvStreamThird.setEnabled(true);
//    }
//
//    class CompentOnTouch implements View.OnTouchListener {
//        public boolean onTouch(View param1View, MotionEvent param1MotionEvent) {
//            switch (param1View.getId()) {
//                default:
//                    return true;
//                case 2131886373:
//                    DaHuaActivity.this.onTouchChange("top", param1MotionEvent.getAction());
//                case 2131886376:
//                    DaHuaActivity.this.onTouchChange("right", param1MotionEvent.getAction());
//                case 2131886377:
//                    DaHuaActivity.this.onTouchChange("bottom", param1MotionEvent.getAction());
//                case 2131886380:
//                    DaHuaActivity.this.onTouchChange("left", param1MotionEvent.getAction());
//                case 2131886374:
//                    DaHuaActivity.this.onTouchChange("leftUp", param1MotionEvent.getAction());
//                case 2131886375:
//                    DaHuaActivity.this.onTouchChange("rightUp", param1MotionEvent.getAction());
//                case 2131886378:
//                    DaHuaActivity.this.onTouchChange("leftDown", param1MotionEvent.getAction());
//                case 2131886379:
//                    break;
//            }
//            DaHuaActivity.this.onTouchChange("rightDown", param1MotionEvent.getAction());
//        }
//    }
//}
