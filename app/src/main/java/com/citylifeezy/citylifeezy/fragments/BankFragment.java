package com.citylifeezy.citylifeezy.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.citylifeezy.citylifeezy.activity.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asif on 9/20/2015.
 */
public class BankFragment extends android.support.v4.app.Fragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, SearchView.OnQueryTextListener {

    private MapView mMapView;
    private GoogleMap mMap;
    public Button update;
    Marker marker;
    Spinner spinner, spinner2;
    LinearLayout linearLayout2;
    ArrayAdapter<String> adapter2;
    private static Context mContext;

    public static BankFragment newInstance(Context context) {
        BankFragment f = new BankFragment();
        Bundle args = new Bundle();
        args.putString("index", context.toString());
        f.setArguments(args);
        mContext = context;
        return f;
    }

    SearchView mySearchView;

    private static final LatLng b_gul = new LatLng(23.774336, 90.415865);
    private static final LatLng b_ngul = new LatLng(23.796726, 90.413519);
    private static final LatLng b_banshundhara = new LatLng(23.813620, 90.428229);
    private static final LatLng b_asad = new LatLng(23.758945, 90.373444);
    private static final LatLng b_banani = new LatLng(23.793986, 90.403531);
    private static final LatLng b_ashkona = new LatLng(23.851338, 90.417352);
    private static final LatLng b_samasjid = new LatLng(23.739002, 90.375439);
    private static final LatLng b_elephant = new LatLng(23.740782, 90.389948);
    private static final LatLng b_eskaton = new LatLng(23.747574, 90.400021);
    private static final LatLng b_mirpur = new LatLng(23.794607, 90.384265);
    private static final LatLng b_begum = new LatLng(23.803113, 90.370106);
    private static final LatLng b_mogbazar = new LatLng(23.749284, 90.407187);
    private static final LatLng b_motijheel = new LatLng(23.729383, 90.416521);
    private static final LatLng b_notun = new LatLng(23.798568, 90.426541);
    private static final LatLng b_banasree = new LatLng(23.764095, 90.430263);
    private static final LatLng b_shymoli = new LatLng(23.772267, 90.360163);
    private static final LatLng b_dakk = new LatLng(23.851334, 90.417353);
    private static final LatLng b_shantinagar = new LatLng(23.741744, 90.412367);
    private static final LatLng b_uttra = new LatLng(23.861462, 90.399574);
    //brac bank atm
    private static final LatLng b_kamal = new LatLng(23.795182, 90.413719);
    private static final LatLng b_banani_1 = new LatLng(23.794702, 90.404721);
    private static final LatLng b_agargoan = new LatLng(23.779302, 90.379393);
    private static final LatLng b_farm = new LatLng(23.757858, 90.383508);
    private static final LatLng b_azim = new LatLng(23.726792, 90.384221);
    private static final LatLng b_gulatm = new LatLng(23.781004, 90.416445);
    private static final LatLng b_prag = new LatLng(23.774179, 90.425635);
    private static final LatLng b_kac = new LatLng(23.795565, 90.387524);
    private static final LatLng b_khilkhet = new LatLng(23.833157, 90.418205);
    private static final LatLng b_bir = new LatLng(23.789199, 90.424937);
    private static final LatLng b_air = new LatLng(23.851393, 90.417328);
    private static final LatLng b_ibra = new LatLng(23.795568, 90.387522);
    private static final LatLng b_sha = new LatLng(23.777106, 90.398615);
    private static final LatLng b_sonar = new LatLng(23.874290, 90.383515);
    private static final LatLng b_new = new LatLng(23.747663, 90.401607);
    private static final LatLng b_khil = new LatLng(23.783623, 90.376955);
    private static final LatLng b_aftab = new LatLng(23.768022, 90.425126);
    private static final LatLng b_kazi = new LatLng(23.760449, 90.389277);
    private static final LatLng b_pant = new LatLng(23.750747, 90.390239);
    private static final LatLng b_begum_rokeya = new LatLng(23.792996, 90.374793);
    private static final LatLng b_moti = new LatLng(23.729739, 90.415867);
    private static final LatLng b_indi = new LatLng(23.757854, 90.383511);
    private static final LatLng b_rampura = new LatLng(23.762258, 90.420081);
    //city bank branch
    private static final LatLng c_dil = new LatLng(23.727060, 90.418716);
    private static final LatLng c_banani = new LatLng(23.791003, 90.402224);
    private static final LatLng c_dhan = new LatLng(23.753306, 90.370649);
    private static final LatLng c_kar = new LatLng(23.750453, 90.391967);
    private static final LatLng c_mirpu = new LatLng(23.797457, 90.353027);
    private static final LatLng c_mog = new LatLng(23.748654, 90.403210);
    private static final LatLng c_moti = new LatLng(23.726933, 90.415779);
    private static final LatLng c_kakra = new LatLng(23.736228, 90.412999);
    private static final LatLng c_dhan_2 = new LatLng(23.739290, 90.382435);
    private static final LatLng c_pall = new LatLng(23.824759, 90.364600);
    private static final LatLng c_bad = new LatLng(23.793837, 90.424404);
    private static final LatLng c_shy = new LatLng(23.773972, 90.366211);
    //city bank atm
    private static final LatLng c_dilkhusa = new LatLng(23.727127, 90.419173);
    private static final LatLng c_rif = new LatLng(23.738168, 90.376679);
    private static final LatLng c_pant = new LatLng(23.750562, 90.391148);
    private static final LatLng c_gre = new LatLng(23.745325, 90.385185);
    private static final LatLng c_wari = new LatLng(23.720170, 90.416932);
    private static final LatLng c_ban = new LatLng(23.790498, 90.407715);
    private static final LatLng c_moh = new LatLng(23.780711, 90.406139);
    private static final LatLng c_baily = new LatLng(23.741848, 90.407687);
    private static final LatLng c_sat = new LatLng(23.746528, 90.370900);
    private static final LatLng c_moni = new LatLng(23.763087, 90.383602);
    private static final LatLng c_pal = new LatLng(23.818694, 90.365229);
    private static final LatLng c_mogb = new LatLng(23.749318, 90.405231);
    private static final LatLng c_rok = new LatLng(23.801175, 90.370990);
    private static final LatLng c_bana = new LatLng(23.763265, 90.431033);
    private static final LatLng c_khil = new LatLng(23.751607, 90.423273);
    private static final LatLng c_gul = new LatLng(23.792674, 90.415374);
    private static final LatLng c_utt = new LatLng(23.874435, 90.399855);
    private static final LatLng c_seg = new LatLng(23.734177, 90.406478);
    private static final LatLng c_mir = new LatLng(23.741320, 90.383045);
    private static final LatLng c_west = new LatLng(23.760202, 90.418590);
    private static final LatLng c_dar = new LatLng(23.788096, 90.353697);
    private static final LatLng c_bash = new LatLng(23.813045, 90.428499);
    private static final LatLng c_fak = new LatLng(23.734327, 90.416518);
    private static final LatLng c_kam = new LatLng(23.731014, 90.425152);
    private static final LatLng c_jan = new LatLng(23.867802, 90.393646);
    private static final LatLng c_ele = new LatLng(23.738882, 90.386158);
    private static final LatLng c_shah = new LatLng(23.793814, 90.424411);
    private static final LatLng c_dhanmondi = new LatLng(23.754796, 90.375911);
    private static final LatLng c_uttara = new LatLng(23.877438, 90.390611);
    private static final LatLng c_mirpur = new LatLng(23.809599, 90.369490);
    private static final LatLng c_elephant = new LatLng(23.738882, 90.386159);
    private static final LatLng c_mirpur_rup = new LatLng(23.816804, 90.355874);
    private static final LatLng c_kak = new LatLng(23.737136, 90.409219);
    private static final LatLng c_dhaka = new LatLng(23.722195, 90.421379);
    private static final LatLng c_rabi = new LatLng(23.867802, 90.393646);
    private static final LatLng c_prag = new LatLng(23.793812, 90.424412);
    private static final LatLng c_merul = new LatLng(23.775504, 90.425698);
    private static final LatLng c_nik = new LatLng(23.836389, 90.417860);
    //united commercial bank branch
    private static final LatLng u_ban = new LatLng(23.793582, 90.409059);
    private static final LatLng u_mad = new LatLng(23.795555, 90.416998);
    private static final LatLng u_gul = new LatLng(23.787405, 90.416437);
    private static final LatLng u_bas = new LatLng(23.812647, 90.426604);
    private static final LatLng u_dhan = new LatLng(23.752829, 90.369830);
    private static final LatLng u_kat = new LatLng(23.736578, 90.386171);
    private static final LatLng u_gulshan = new LatLng(23.770158, 90.408425);
    private static final LatLng u_kar = new LatLng(23.750709, 90.393611);
    private static final LatLng u_esk = new LatLng(23.748647, 90.402302);
    private static final LatLng u_aftab = new LatLng(23.768555, 90.425944);
    private static final LatLng u_sidd = new LatLng(23.742190, 90.411799);
    private static final LatLng u_sha = new LatLng(23.732663, 90.410330);
    private static final LatLng u_moti = new LatLng(23.728317, 90.418898);
    //united commercial bank atm
    private static final LatLng u_bash = new LatLng(23.815358, 90.426890);
    private static final LatLng u_gus = new LatLng(23.787466, 90.416652);
    private static final LatLng u_ring = new LatLng(23.772554, 90.360266);
    private static final LatLng u_asad = new LatLng(23.760036, 90.367677);
    private static final LatLng u_kal = new LatLng(23.747353, 90.380323);
    private static final LatLng u_dhanmo = new LatLng(23.743065, 90.382650);
    private static final LatLng u_bir = new LatLng(23.770202, 90.408440);
    private static final LatLng u_lal = new LatLng(23.719904, 90.389206);
    private static final LatLng u_pant = new LatLng(23.752749, 90.384652);
    private static final LatLng u_new = new LatLng(23.738746, 90.390130);
    private static final LatLng u_motijheel = new LatLng(23.727273, 90.420754);
    //dutch bangla bank branch
    private static final LatLng d_sonar = new LatLng(23.874470, 90.393024);
    private static final LatLng d_dit = new LatLng(23.759117, 90.418314);
    private static final LatLng d_kazi = new LatLng(23.750912, 90.393319);
    private static final LatLng d_shanti = new LatLng(23.741146, 90.412689);
    private static final LatLng d_ash = new LatLng(23.851542, 90.414309);
    private static final LatLng d_khil = new LatLng(23.833111, 90.417701);
    private static final LatLng d_ring = new LatLng(23.770613, 90.358948);
    private static final LatLng d_ele = new LatLng(23.738913, 90.388109);
    private static final LatLng d_dil = new LatLng(23.727063, 90.416806);
    private static final LatLng d_sat = new LatLng(23.745034, 90.372514);
    //dutch bangla bank atm
    private static final LatLng d_cant = new LatLng(23.827546, 90.401512);
    private static final LatLng d_utt = new LatLng(23.862401, 90.399578);
    private static final LatLng d_vas = new LatLng(23.811642, 90.395949);
    private static final LatLng d_sha = new LatLng(23.858107, 90.409380);
    private static final LatLng d_shah = new LatLng(23.868154, 90.410152);
    private static final LatLng d_air = new LatLng(23.851516, 90.411621);
    private static final LatLng d_ashkona = new LatLng(23.851513, 90.411622);
    private static final LatLng d_gau = new LatLng(23.869263, 90.390369);
    private static final LatLng d_sonargoan = new LatLng(23.874466, 90.393036);
    private static final LatLng d_taj = new LatLng(23.769976, 90.400535);
    private static final LatLng d_ram = new LatLng(23.763409, 90.415036);
    private static final LatLng d_banasree = new LatLng(23.762767, 90.431002);
    private static final LatLng d_khilkhet = new LatLng(23.744349, 90.425749);
    private static final LatLng d_new = new LatLng(23.748404, 90.402333);
    private static final LatLng d_karwan = new LatLng(23.750145, 90.392700);
    private static final LatLng d_shantinagar = new LatLng(23.739636, 90.414503);
    private static final LatLng d_baily = new LatLng(23.741860, 90.407944);
    private static final LatLng d_shahbag = new LatLng(23.738475, 90.395545);
    private static final LatLng d_moti = new LatLng(23.737188, 90.409192);
    //HSBC bank branch
    private static final LatLng h_dha = new LatLng(23.7548909, 90.3731717);
    private static final LatLng h_gul = new LatLng(23.7765711, 90.4162442);
    private static final LatLng h_mir = new LatLng(23.8210378, 90.3650808);
    private static final LatLng h_sonar = new LatLng(23.7480941, 90.3922999);
    //hsbc bank atm
    private static final LatLng h_ram = new LatLng(23.763063, 90.4309479);
    private static final LatLng h_gul2 = new LatLng(23.7933469, 90.4146873);
    private static final LatLng h_gul1 = new LatLng(23.7765711, 90.4162442);
    private static final LatLng h_bash = new LatLng(23.8220163, 90.4293221);
    private static final LatLng h_chak = new LatLng(23.7160321, 90.394853);
    //s & c bank branch
    private static final LatLng s_gul = new LatLng(23.7837894, 90.4165296);
    private static final LatLng s_satm = new LatLng(23.7471194, 90.3704627);
    private static final LatLng s_dhan = new LatLng(23.7384645, 90.3782482);
    private static final LatLng s_beg = new LatLng(23.8149212, 90.3662526);
    private static final LatLng s_banasree = new LatLng(23.7638451, 90.4305723);
    private static final LatLng s_chaw = new LatLng(23.7158773, 90.3945917);
    private static final LatLng s_khil = new LatLng(23.7510574, 90.4239607);
    private static final LatLng s_utt = new LatLng(23.8683184, 90.4016769);
    //s & c bank atm
    private static final LatLng s_ring = new LatLng(23.763647, 90.3589357);
    private static final LatLng s_begum = new LatLng(23.7967497, 90.3728852);
    private static final LatLng s_mog = new LatLng(23.7475118, 90.4100339);
    private static final LatLng s_khilkhet = new LatLng(23.832977, 90.4189355);
    private static final LatLng s_bangla = new LatLng(23.7298858, 90.3987205);
    private static final LatLng s_lal = new LatLng(23.7200226, 90.387224);
    private static final LatLng s_banani = new LatLng(23.7909333, 90.4029992);
    private static final LatLng s_sat = new LatLng(23.7435174, 90.373336);
    private static final LatLng s_dhanmon = new LatLng(23.7422996, 90.3829559);
    private static final LatLng s_gulshan = new LatLng(23.7954859, 90.4157183);
    private static final LatLng s_gul1 = new LatLng(23.7781483, 90.4168054);
    private static final LatLng s_farm = new LatLng(23.7621474, 90.3893772);
    private static final LatLng s_bashu = new LatLng(23.7510779, 90.3905812);
    private static final LatLng s_agar = new LatLng(23.7824909, 90.3712888);
    private static final LatLng s_dit = new LatLng(23.7569595, 90.4167261);
    private static final LatLng s_gre = new LatLng(23.7509567, 90.386788);
    private static final LatLng s_new = new LatLng(23.7418298, 90.4075697);
    private static final LatLng s_segu = new LatLng(23.7332252, 90.4104012);
    private static final LatLng s_uttara = new LatLng(23.8680044, 90.4016259);
    //prime bank branch
    private static final LatLng p_ban = new LatLng(23.7932084, 90.4094231);
    private static final LatLng p_gul = new LatLng(23.7907537, 90.4163104);
    private static final LatLng p_bas = new LatLng(23.8129421, 90.4269899);
    private static final LatLng p_kach = new LatLng(23.79548, 90.3875256);
    private static final LatLng p_moh = new LatLng(23.7802334, 90.406446);
    private static final LatLng p_tej = new LatLng(23.7704163, 90.4065505);
    private static final LatLng p_tejturi = new LatLng(23.7541617, 90.3921644);
    private static final LatLng p_mid = new LatLng(23.7752851, 90.4256778);
    private static final LatLng p_dhanmond = new LatLng(23.7416802, 90.3825821);
    private static final LatLng p_asad = new LatLng(23.7630448, 90.3711373);
    private static final LatLng p_sat = new LatLng(23.7467119, 90.3712633);
    private static final LatLng p_new = new LatLng(23.7474728, 90.4007016);
    private static final LatLng p_ele = new LatLng(23.7408244, 90.3898913);
    private static final LatLng p_beg = new LatLng(23.8009538, 90.3710353);
    private static final LatLng p_banasree = new LatLng(23.7638039, 90.4309736);
    private static final LatLng p_ring = new LatLng(23.7704213, 90.3588474);
    private static final LatLng p_mali = new LatLng(23.7441233, 90.4139535);
    private static final LatLng p_seg = new LatLng(23.7339307, 90.4098659);
    private static final LatLng p_mir = new LatLng(23.7987144, 90.3517972);
    private static final LatLng p_moti = new LatLng(23.7285501, 90.4160025);
    private static final LatLng p_south = new LatLng(23.740213, 90.431546);
    private static final LatLng p_dil = new LatLng(23.7262616, 90.4198461);
    private static final LatLng p_dit = new LatLng(23.7260365, 90.4207291);
    private static final LatLng p_lal = new LatLng(23.7170163, 90.3897357);
    private static final LatLng p_utt = new LatLng(23.8681732, 90.4022107);
    //prime bank atm
    private static final LatLng p_taj = new LatLng(23.7704163, 90.4065505);
    private static final LatLng p_bij = new LatLng(23.7644563, 90.3923884);
    private static final LatLng p_pant = new LatLng(23.7514315, 90.3862546);
    private static final LatLng p_satmas = new LatLng(23.7443402, 90.3723925);
    private static final LatLng p_ram = new LatLng(23.7621527, 90.4200255);
    private static final LatLng p_elephant = new LatLng(23.7391816, 90.3891121);
    private static final LatLng p_tajmahal = new LatLng(23.7640852, 90.3651327);
    private static final LatLng p_shy = new LatLng(23.7756105, 90.3648864);
    private static final LatLng p_dhanmondi = new LatLng(23.7380071, 90.3767614);
    private static final LatLng p_begu = new LatLng(23.8224511, 90.3641929);
    private static final LatLng p_ramna = new LatLng(23.7425553, 90.4057217);
    private static final LatLng p_lalbag = new LatLng(23.7258006, 90.3964598);
    private static final LatLng p_shidd = new LatLng(23.7428659, 90.4125733);
    private static final LatLng p_tal = new LatLng(23.7516748, 90.4230997);
    private static final LatLng p_begum = new LatLng(23.8224511, 90.3641929);
    private static final LatLng p_rup = new LatLng(23.8162149, 90.3561558);
    private static final LatLng p_dilkhusa = new LatLng(23.7262616, 90.4198461);
    private static final LatLng p_uttara = new LatLng(23.8761023, 90.3905753);
    //eastern bank branch
    private static final LatLng e_mir = new LatLng(23.817086, 90.3660303);
    private static final LatLng e_mirpu = new LatLng(23.7871134, 90.3631598);
    private static final LatLng e_ban = new LatLng(23.79098, 90.4042089);
    private static final LatLng e_utt = new LatLng(23.861411, 90.3997086);
    private static final LatLng e_ring = new LatLng(23.7676703, 90.3586268);
    private static final LatLng e_dak = new LatLng(23.8515412, 90.4156639);
    private static final LatLng e_uttara = new LatLng(23.8592923, 90.401661);
    private static final LatLng e_uttara_gr = new LatLng(23.8771489, 90.3906229);
    private static final LatLng e_kal = new LatLng(23.7454434, 90.38132);
    private static final LatLng e_bash = new LatLng(23.8128049, 90.4286677);
    private static final LatLng e_gul2 = new LatLng(23.7826429, 90.4169975);
    private static final LatLng e_gulave = new LatLng(23.7954859, 90.4157183);
    private static final LatLng e_sonargoan = new LatLng(23.7462694, 90.3928781);
    private static final LatLng e_gula = new LatLng(23.7963964, 90.4135618);
    private static final LatLng e_mog = new LatLng(23.748125, 90.4093607);
    private static final LatLng e_shan = new LatLng(23.7416948, 90.4126324);
    private static final LatLng e_kak = new LatLng(23.7386381, 90.4094347);
    private static final LatLng e_purana = new LatLng(23.7304678, 90.4137522);
    private static final LatLng e_pil = new LatLng(23.7266717, 90.3839126);
    private static final LatLng e_shant = new LatLng(23.7329959, 90.412602);
    private static final LatLng e_north = new LatLng(23.7159946, 90.4084939);
    //eastern bank atm
    private static final LatLng e_rupnagar = new LatLng(23.816448, 90.3561088);
    private static final LatLng e_uttara_lke = new LatLng(23.8688899, 90.393649);
    private static final LatLng e_moh = new LatLng(23.768676, 90.3687152);
    private static final LatLng e_azam = new LatLng(23.8689996, 90.4015896);
    private static final LatLng e_shmoli = new LatLng(23.7748494, 90.3651935);
    private static final LatLng e_moha = new LatLng(23.7619747, 90.35721);
    private static final LatLng e_tajmohol = new LatLng(23.7629834, 90.3612936);
    private static final LatLng e_gulshan = new LatLng(23.795077, 90.4135079);
    private static final LatLng e_new = new LatLng(23.7812276, 90.3980048);
    private static final LatLng e_uttara_garib = new LatLng(23.8769383, 90.3875599);
    private static final LatLng e_azad = new LatLng(23.7594866, 90.3731451);
    private static final LatLng e_bijoy = new LatLng(23.764466, 90.3924823);
    private static final LatLng e_mohakhali = new LatLng(23.7808321, 90.4050766);
    private static final LatLng e_farmgate = new LatLng(23.760358, 90.3893123);
    private static final LatLng e_shi = new LatLng(23.7381796, 90.376668);
    private static final LatLng e_mirpur = new LatLng(23.8068711, 90.3620536);
    private static final LatLng e_karwan = new LatLng(23.7546675, 90.3915985);
    private static final LatLng e_elep = new LatLng(23.7368733, 90.3863286);
    private static final LatLng e_gulshan_nav = new LatLng(23.7709369, 90.4111763);
    private static final LatLng e_moni = new LatLng(23.7612405, 90.3837276);
    private static final LatLng e_pant = new LatLng(23.7503957, 90.3917594);
    private static final LatLng e_new_b = new LatLng(23.7418877, 90.4084465);
    private static final LatLng e_shidd = new LatLng(23.7428904, 90.4125961);
    private static final LatLng e_shanr = new LatLng(23.7392307, 90.4151413);
    private static final LatLng e_dilkh = new LatLng(23.7287644, 90.4181743);
    //AB bank branches
    private static final LatLng ab_dhan = new LatLng(23.7527181, 90.3695963);
    private static final LatLng ab_gul = new LatLng(23.7879006, 90.4163906);
    private static final LatLng ab_vip = new LatLng(23.7373526, 90.407095);
    private static final LatLng ab_kazi = new LatLng(23.7520001, 90.3924997);
    private static final LatLng ab_mali = new LatLng(23.7459827, 90.4126927);
    private static final LatLng ab_mir = new LatLng(23.7969707, 90.3535715);
    private static final LatLng ab_dit = new LatLng(23.7269688, 90.4197201);
    private static final LatLng ab_moha = new LatLng(23.7811337, 90.4018362);
    private static final LatLng ab_bari = new LatLng(23.8040656, 90.4218149);
    private static final LatLng ab_she = new LatLng(23.7911603, 90.3755531);
    private static final LatLng ab_shymoli = new LatLng(23.7746793, 90.3655362);
    private static final LatLng ab_uttara = new LatLng(23.8682374, 90.406293);
    //AB bank atm
    private static final LatLng ab_dhanmondi = new LatLng(23.742985, 90.3732723);
    private static final LatLng ab_dhanmondi_1 = new LatLng(23.74846, 90.3741682);
    private static final LatLng ab_dhanmondi_2 = new LatLng(23.7393021, 90.3825662);
    private static final LatLng ab_dhanmondi_3 = new LatLng(23.7527181, 90.3695963);
    private static final LatLng ab_dhanmondi_4 = new LatLng(23.7496385, 90.3755447);
    private static final LatLng ab_dhanmondi_5 = new LatLng(23.7456382, 90.3808771);
    private static final LatLng ab_ban = new LatLng(23.8169685, 90.3566534);
    private static final LatLng ab_baridhara = new LatLng(23.8131096, 90.4104397);
    private static final LatLng ab_gulsh = new LatLng(23.7879158, 90.4163942);
    private static final LatLng ab_south_gul = new LatLng(23.7393021, 90.3825662);
    private static final LatLng ab_ring = new LatLng(23.8169685, 90.3566534);
    private static final LatLng ab_gulshan = new LatLng(23.7879006, 90.4163906);
    private static final LatLng ab_kak = new LatLng(23.7520001, 90.3924997);
    //private static final LatLng ab_dhan_pilk = new LatLng();
    private static final LatLng ab_kazi_nazrul = new LatLng(23.7520001, 90.3924997);
    //private static final LatLng ab_malibagh = new LatLng();
    private static final LatLng ab_malibagh_1 = new LatLng(23.7459827, 90.4126927);
    private static final LatLng ab_bashu = new LatLng(23.8169685, 90.3566534);
    private static final LatLng ab_manik = new LatLng(23.8169685, 90.3566534);
    private static final LatLng ab_mirp = new LatLng(23.8012471, 90.3709415);
    private static final LatLng ab_mir_1 = new LatLng(23.7790234, 90.3559573);
    private static final LatLng ab_mir_2 = new LatLng(23.8169685, 90.3566534);
    private static final LatLng ab_mir_rup = new LatLng(23.816782, 90.3558595);
    private static final LatLng ab_mir_sec = new LatLng(23.7756105, 90.3648864);
    private static final LatLng ab_utt = new LatLng(23.8682374, 90.406293);
    private static final LatLng ab_sec_utt = new LatLng(23.8644753, 90.3946406);
    private static final LatLng ab_dak = new LatLng(23.8516635, 90.4151968);
    //bank asia branches
    private static final LatLng bn_bashu = new LatLng(23.729775, 90.4112312);
    private static final LatLng bn_sonar = new LatLng(23.7484933, 90.3926112);
    private static final LatLng bn_dak = new LatLng(23.8614154, 90.3965815);
    private static final LatLng bn_meher = new LatLng(23.7422229, 90.3825076);
    private static final LatLng bn_ele = new LatLng(23.73708, 90.386517);
    private static final LatLng bn_eska = new LatLng(23.7466664, 90.4027122);
    private static final LatLng bn_gul = new LatLng(23.7704098, 90.4076125);
    private static final LatLng bn_ban = new LatLng(23.7937936, 90.4039592);
    private static final LatLng bn_dil = new LatLng(23.7296839, 90.4115125);
    private static final LatLng bn_pallabi = new LatLng(23.815437, 90.3660035);
    private static final LatLng bn_moha = new LatLng(23.7803416, 90.4039778);
    private static final LatLng bn_pal = new LatLng(23.729775, 90.4112312);
    private static final LatLng bn_moti = new LatLng(23.7285206, 90.416767);
    private static final LatLng bn_progati = new LatLng(23.7755206, 90.425673);
    private static final LatLng bn_ring = new LatLng(23.7702629, 90.358787);
    private static final LatLng bn_rup_mir = new LatLng(23.8151203, 90.3559243);
    private static final LatLng bn_bijoy = new LatLng(23.8151203, 90.3559243);
    private static final LatLng bn_cir = new LatLng(23.7430968, 90.412856);
    private static final LatLng bn_shymoli = new LatLng(23.7737709, 90.3665808);
    private static final LatLng bn_taj = new LatLng(23.770517, 90.4061374);
    private static final LatLng bn_utt = new LatLng(23.8624008, 90.4003318);
    //bank asia atm
    private static final LatLng bn_bashundhara = new LatLng(23.729775, 90.4112312);
    private static final LatLng bn_eskaton = new LatLng(23.7443942, 90.4012263);
    private static final LatLng bn_sonargaon = new LatLng(23.7368933, 90.387576);
    private static final LatLng bn_dhanmond = new LatLng(23.7421271, 90.3825103);
    private static final LatLng bn_dilku = new LatLng(23.7297524, 90.4152601);
    private static final LatLng bn_garden = new LatLng(23.7402776, 90.3968679);
    private static final LatLng bn_bays = new LatLng(23.7822314, 90.4164304);
    private static final LatLng bn_sat = new LatLng(23.7528556, 90.3668546);
    private static final LatLng bn_mirpur = new LatLng(23.8153831, 90.3660679);
    private static final LatLng bn_moham = new LatLng(24.9162025, 88.7518099);
    private static final LatLng bn_kamla = new LatLng(23.7326801, 90.4239848);
    private static final LatLng bn_paltan = new LatLng(23.7299423, 90.4112255);
    private static final LatLng bn_ring_road = new LatLng(23.7702629, 90.358787);
    private static final LatLng bn_shym = new LatLng(23.7737709, 90.3665808);
    private static final LatLng bn_nazrul_avenue = new LatLng(23.750608, 90.3925683);
    //exim bank branches
    private static final LatLng ex_pragati = new LatLng(23.8126495, 90.420962);
    private static final LatLng ex_gul = new LatLng(23.775654, 90.416557);
    private static final LatLng ex_pantha = new LatLng(23.7530312, 90.3820056);
    private static final LatLng ex_bgmea = new LatLng(23.7506842, 90.3972137);
    private static final LatLng ex_gul2 = new LatLng(23.7953743, 90.4141586);
    private static final LatLng ex_rokeya = new LatLng(23.8060605, 90.3691381);
    private static final LatLng ex_dit = new LatLng(23.7509, 90.4132026);
    private static final LatLng ex_pra = new LatLng(23.8126495, 90.420962);
    private static final LatLng ex_ele = new LatLng(23.7393414, 90.389092);
    private static final LatLng ex_moti = new LatLng(23.7271183, 90.4205158);
    private static final LatLng ex_sat = new LatLng(23.7461865, 90.3709066);
    private static final LatLng ex_rokeya_sarani = new LatLng(23.8060605, 90.3691381);
    private static final LatLng ex_ring = new LatLng(23.7646821, 90.3589185);
    private static final LatLng ex_nawabpur = new LatLng(23.7196954, 90.4116413);
    private static final LatLng ex_garib = new LatLng(23.8779993, 90.3905511);
    //exim bank atm
    private static final LatLng ex_dhk = new LatLng(23.790067, 90.4092354);
    //private static final LatLng ex_dhaka = new LatLng();
    private static final LatLng ex_ring_road = new LatLng(23.764687, 90.358901);
    //Mercantile bank branch
    private static final LatLng m_pragati = new LatLng(23.8099979, 90.4214758);
    private static final LatLng m_kachu = new LatLng(23.7950258, 90.3879373);
    private static final LatLng m_rokeya = new LatLng(23.8043108, 90.3699642);
    private static final LatLng m_gul = new LatLng(23.788066, 90.4170927);
    private static final LatLng m_moha = new LatLng(23.7846124, 90.3991427);
    private static final LatLng m_moha_bir = new LatLng(23.780512, 90.4077226);
    private static final LatLng m_ring = new LatLng(23.7736296, 90.3614704);
    private static final LatLng m_dit = new LatLng(23.7584007, 90.4176776);
    private static final LatLng m_dhaka = new LatLng(23.7560705, 90.3748763);
    private static final LatLng m_pantha = new LatLng(23.7508757, 90.3868362);
    private static final LatLng m_kazi = new LatLng(23.7527366, 90.3924394);
    private static final LatLng m_sat = new LatLng(23.7444758, 90.3721505);
    private static final LatLng m_cir = new LatLng(23.7494542, 90.4061455);
    private static final LatLng m_jaha = new LatLng(23.7379493, 90.3874612);
    private static final LatLng m_moulana = new LatLng(23.7339484, 90.4017251);
    private static final LatLng m_sharani = new LatLng(23.7319779, 90.4099622);
    private static final LatLng m_malek = new LatLng(23.7295384, 90.4167563);
    private static final LatLng m_pass = new LatLng(23.7261049, 90.4166928);
    private static final LatLng m_moulo = new LatLng(23.7148013, 90.3979754);
    private static final LatLng m_lal = new LatLng(23.7115671, 90.4164371);
    private static final LatLng m_indira = new LatLng(23.7582087, 90.3880607);
    private static final LatLng m_asad = new LatLng(23.7571728, 90.3614719);
    private static final LatLng m_manikdi = new LatLng(23.8254703, 90.3934934);
    private static final LatLng m_ibra = new LatLng(23.7939153, 90.3874585);
    private static final LatLng m_shahid = new LatLng(23.8581326, 90.4090248);
    //Mercantile bank atm
    private static final LatLng m_mazar = new LatLng(23.7968078, 90.3499447);
    private static final LatLng m_dhk = new LatLng(23.8687182, 90.3936571);
    private static final LatLng m_haka = new LatLng(23.8778161, 90.4009572);
    private static final LatLng m_shezan = new LatLng(23.7582676, 90.3896351);
    private static final LatLng m_shahid_baki = new LatLng(23.7504289, 90.425393);
    private static final LatLng m_atish = new LatLng(23.7383151, 90.4285714);
    private static final LatLng m_purana_paltan = new LatLng(23.732464, 90.4128402);
    private static final LatLng m_dit_1 = new LatLng(23.7277442, 90.4151725);
    private static final LatLng m_north_kamla = new LatLng(23.7305636, 90.4251784);
    private static final LatLng m_pilkhana = new LatLng(23.7262301, 90.3801073);
    private static final LatLng m_rankin = new LatLng(23.7171734, 90.4164317);
    private static final LatLng m_vasani = new LatLng(23.7339484, 90.4017251);
    //Mutual Trust Bank branch
    private static final LatLng mu_pragati = new LatLng(23.8116275, 90.4214177);
    private static final LatLng mu_dhk = new LatLng(23.8129022, 90.4270399);
    private static final LatLng mu_dhk1 = new LatLng(23.7908327, 90.4048955);
    private static final LatLng mu_gul = new LatLng(23.777453, 90.4169008);
    private static final LatLng mu_mir = new LatLng(23.8070619, 90.3699276);
    private static final LatLng mu_dhk2 = new LatLng(23.8277914, 90.3640563);
    private static final LatLng mu_bir = new LatLng(23.7909572, 90.4246594);
    private static final LatLng mu_gulshan = new LatLng(23.777453, 90.4169008);
    private static final LatLng mu_dhk3 = new LatLng(23.8617891, 90.4002975);
    private static final LatLng mu_bir_uttam = new LatLng(23.7703717, 90.4093969);
    private static final LatLng mu_service = new LatLng(23.8604544, 90.4008635);
    private static final LatLng mu_dhk4 = new LatLng(23.8696061, 90.3838134);
    private static final LatLng mu_shah = new LatLng(23.8758813, 90.3848716);
    private static final LatLng mu_moha = new LatLng(23.7589366, 90.3644624);
    private static final LatLng mu_hatir = new LatLng(23.7510132, 90.3905216);
    private static final LatLng mu_chandra = new LatLng(23.7505491, 90.3884664);
    private static final LatLng mu_dhk5 = new LatLng(23.7442106, 90.3730589);
    private static final LatLng mu_ele = new LatLng(23.7388847, 90.3890115);
    private static final LatLng mu_uttara = new LatLng(23.8598568, 90.3783337);
    private static final LatLng mu_samsul = new LatLng(23.7386035, 90.4093596);
    private static final LatLng mu_central = new LatLng(23.7416554, 90.3914509);
    private static final LatLng mu_moti = new LatLng(23.7285009, 90.4185945);
    private static final LatLng mu_ramna = new LatLng(23.7377221, 90.4035304);
    private static final LatLng mu_dil = new LatLng(23.7261523, 90.4167093);
    private static final LatLng mu_motijheel = new LatLng(23.7279256, 90.4196746);
    private static final LatLng mu_kazi = new LatLng(23.7465204, 90.3948963);
    private static final LatLng mu_chowk = new LatLng(23.7156374, 90.3972271);
    //Mutual Trust Bank atm
    private static final LatLng mu_dhk6 = new LatLng(23.8157082, 90.4277211);
    private static final LatLng mu_dit = new LatLng(23.7942633, 90.414072);
    private static final LatLng mu_kemal = new LatLng(23.7939332, 90.4032655);
    private static final LatLng mu_dhk7 = new LatLng(23.7874637, 90.4157894);
    private static final LatLng mu_terminal = new LatLng(23.8476149, 90.4054454);
    private static final LatLng mu_dhk8 = new LatLng(23.8283525, 90.402946);
    private static final LatLng mu_jashi = new LatLng(23.8612492, 90.398878);
    private static final LatLng mu_sonar = new LatLng(23.874492, 90.3917666);
    private static final LatLng mu_dhk9 = new LatLng(23.8409886, 90.384016);
    private static final LatLng mu_abc = new LatLng(23.8733059, 90.3909424);
    private static final LatLng mu_pantha = new LatLng(23.7527341, 90.3815022);
    private static final LatLng mu_esk = new LatLng(23.7468941, 90.3978164);
    private static final LatLng mu_dit_road = new LatLng(23.7529434, 90.4147795);
    private static final LatLng mu_kata = new LatLng(23.7386646, 90.3907462);
    private static final LatLng mu_toyen = new LatLng(23.7218263, 90.4213811);
    private static final LatLng mu_hatkola = new LatLng(23.7202066, 90.4237548);
    //premier bank branch
    private static final LatLng pr_gul = new LatLng(23.7751114, 90.4023965);
    private static final LatLng pr_kemal = new LatLng(23.7921353, 90.4044778);
    private static final LatLng pr_dhk1 = new LatLng(23.790122, 90.4105832);
    private static final LatLng pr_bir = new LatLng(23.780863, 90.4014623);
    private static final LatLng pr_mir = new LatLng(23.7468945, 90.3810403);
    private static final LatLng pr_begum = new LatLng(23.7896937, 90.3762256);
    private static final LatLng pr_agrani = new LatLng(23.7634472, 90.4204157);
    private static final LatLng pr_ring = new LatLng(23.7747267, 90.3653035);
    private static final LatLng pr_new = new LatLng(23.7336155, 90.3817785);
    private static final LatLng pr_vip = new LatLng(23.7371755, 90.4090791);
    private static final LatLng pr_air = new LatLng(23.8515455, 90.414425);
    private static final LatLng pr_dhk2 = new LatLng(23.8615484, 90.4004538);
    private static final LatLng pr_momen = new LatLng(23.7144476, 90.4692766);
    //one bank branch
    private static final LatLng o_kak = new LatLng(23.709921, 90.407143);
    private static final LatLng o_alam = new LatLng(23.7384452, 90.4089725);
    private static final LatLng o_lal = new LatLng(23.7198003, 90.3901505);
    private static final LatLng o_al = new LatLng(23.7617547, 90.4305656);
    private static final LatLng o_selina = new LatLng(23.7483894, 90.4089514);
    private static final LatLng o_dhk1 = new LatLng(23.7455101, 90.3812975);
    //one bank atm
    private static final LatLng o_dil = new LatLng(23.7265845, 90.4184434);
    private static final LatLng o_kawran = new LatLng(23.7455101, 90.3812975);
    private static final LatLng o_lalbag = new LatLng(23.7197987, 90.390103);
    private static final LatLng o_dak = new LatLng(23.7197987, 90.390103);
    //south east bank branch
    private static final LatLng se_simpl = new LatLng(23.8128127, 90.3667465);
    private static final LatLng se_begum = new LatLng(23.7842181, 90.3781512);
    private static final LatLng se_dhk1 = new LatLng(23.7545815, 90.3725924);
    private static final LatLng se_bir = new LatLng(23.7805611, 90.4075402);
    private static final LatLng se_gul = new LatLng(23.7848248, 90.4172862);
    private static final LatLng se_islampur = new LatLng(23.7113583, 90.4040238);
    private static final LatLng se_eska = new LatLng(23.7474891, 90.3997625);
    private static final LatLng se_mir = new LatLng(23.7734282, 90.3665158);
    private static final LatLng se_dhk2 = new LatLng(23.8068553, 90.3797445);
    private static final LatLng se_ele = new LatLng(23.7392002, 90.3860866);
    private static final LatLng se_rafi = new LatLng(23.7927573, 90.4246078);
    private static final LatLng se_bana = new LatLng(23.7637597, 90.4308926);
    private static final LatLng se_mog = new LatLng(23.7489018, 90.4048017);
    //south east bank atm
    private static final LatLng se_rangan = new LatLng(23.8132286, 90.3565313);
    private static final LatLng se_moti = new LatLng(23.7323046, 90.4116819);
    private static final LatLng se_dhk3 = new LatLng(23.8660007, 90.3887913);
    private static final LatLng se_shahid = new LatLng(23.746383, 90.4114442);
    private static final LatLng se_north = new LatLng(23.8153512, 90.4269433);
    private static final LatLng se_purabi = new LatLng(23.8208002, 90.3645765);
    private static final LatLng se_dhk4 = new LatLng(23.7379505, 90.3768973);
    private static final LatLng se_panthapath = new LatLng(23.7526961, 90.3826078);
    //hongkong bank branches
    private static final LatLng hk_dhk1 = new LatLng(23.7548909, 90.3731717);
    private static final LatLng hk_dhk2 = new LatLng(23.7548909, 90.3731717);
    private static final LatLng hk_begum = new LatLng(23.8210378, 90.3650808);
    //hongkong bank atm
    private static final LatLng hk_dhk3 = new LatLng(23.763063, 90.4309479);
    private static final LatLng hk_gul = new LatLng(23.7933469, 90.4146873);
    private static final LatLng hk_bir = new LatLng(23.7765711, 90.4162442);
    private static final LatLng hk_bir_uttam = new LatLng(23.7480941, 90.3922999);
    private static final LatLng hk_bashu = new LatLng(23.8220163, 90.4293221);
    private static final LatLng hk_water = new LatLng(23.7160321, 90.394853);
    //standard bank branch
    private static final LatLng st_gul = new LatLng(23.7887842, 90.4069815);
    private static final LatLng st_bir = new LatLng(23.7805001, 90.4058438);
    private static final LatLng st_pragati = new LatLng(23.8171217, 90.4208325);
    private static final LatLng st_moha = new LatLng(23.7682685, 90.3583337);
    private static final LatLng st_dhan = new LatLng(23.7562799, 90.3726046);
    private static final LatLng st_lions = new LatLng(23.8013857, 90.3548777);
    private static final LatLng st_dhk1 = new LatLng(23.7910855, 90.400612);
    private static final LatLng st_cresent = new LatLng(23.7302223, 90.4094982);
    private static final LatLng st_moti = new LatLng(23.7241325, 90.4135572);
    private static final LatLng st_air = new LatLng(23.7887842, 90.4069815);
    private static final LatLng st_shahi = new LatLng(23.7209895, 90.4111263);
    private static final LatLng st_momen = new LatLng(23.711179, 90.4677987);
    //standard atm
    private static final LatLng st_zoo = new LatLng(23.8013341, 90.3524047);
    //trust bank barnch
    private static final LatLng tr_bir = new LatLng(23.780728, 90.4067287);
    private static final LatLng tr_gul = new LatLng(23.7886249, 90.4144701);
    private static final LatLng tr_kemal = new LatLng(23.7938331, 90.4025727);
    private static final LatLng tr_cant = new LatLng(23.7756778, 90.390586);
    private static final LatLng tr_north1 = new LatLng(23.7905284, 90.3877603);
    private static final LatLng tr_shaheed = new LatLng(23.7984997, 90.3891832);
    private static final LatLng tr_trade = new LatLng(23.7545993, 90.391637);
    private static final LatLng tr_pragati = new LatLng(23.814534, 90.3946388);
    private static final LatLng tr_kakrail = new LatLng(23.7626004, 90.3627768);
    private static final LatLng tr_pilkhana = new LatLng(23.738125, 90.3738015);
    private static final LatLng tr_sena = new LatLng(23.7269197, 90.4227564);
    private static final LatLng tr_dil = new LatLng(23.7261059, 90.4185423);
    private static final LatLng tr_moha = new LatLng(23.7632681, 90.3709656);
    //trust bank atm
    private static final LatLng tr_canto = new LatLng(23.7877795, 90.39473);
    private static final LatLng tr_shahed = new LatLng(23.7756778, 90.390586);
    private static final LatLng tr_saudi = new LatLng(23.7810397, 90.3874451);
    private static final LatLng tr_falcon = new LatLng(23.77184, 90.3879011);
    private static final LatLng tr_rajni = new LatLng(23.7879243, 90.3864097);
    private static final LatLng tr_south = new LatLng(23.7905284, 90.3877603);
    private static final LatLng tr_navy = new LatLng(23.780728, 90.4067287);
    private static final LatLng tr_army = new LatLng(23.7984997, 90.3891832);
    private static final LatLng tr_cmh = new LatLng(23.8136133, 90.3972561);
    private static final LatLng tr_prese = new LatLng(23.7652663, 90.3705096);
    private static final LatLng tr_bhan = new LatLng(23.814534, 90.3946388);
    private static final LatLng tr_apollo = new LatLng(23.8119356, 90.4281309);
    private static final LatLng tr_dhan = new LatLng(23.754611, 90.3726487);
    private static final LatLng tr_moham = new LatLng(23.7626004, 90.3627768);
    private static final LatLng tr_kak = new LatLng(23.7373119, 90.4039621);
    private static final LatLng tr_sat = new LatLng(23.7380843, 90.3745464);
    private static final LatLng tr_mir = new LatLng(23.8184454, 90.3656092);
    private static final LatLng tr_north = new LatLng(23.7359335, 90.3739858);
    private static final LatLng tr_moti = new LatLng(23.7359335, 90.3739858);
    private static final LatLng tr_polashi = new LatLng(23.7267847, 90.3864586);
    private static final LatLng tr_new = new LatLng(23.7305808, 90.3780681);
    //uttara bank branch
    private static final LatLng ut_bir = new LatLng(23.7809587, 90.4055312);
    private static final LatLng ut_kazi = new LatLng(23.7620432, 90.3893789);
    private static final LatLng ut_shahid = new LatLng(23.7558225, 90.4164532);
    private static final LatLng ut_green = new LatLng(23.7495752, 90.3793933);
    private static final LatLng ut_begum = new LatLng(23.7916205, 90.3750211);
    private static final LatLng ut_outer = new LatLng(23.770296, 90.3906852);
    private static final LatLng ut_selina = new LatLng(23.7488072, 90.4084076);
    private static final LatLng ut_pantha = new LatLng(23.7809587, 90.4055312);
    private static final LatLng ut_ring = new LatLng(23.77495, 90.3652901);
    private static final LatLng ut_lake = new LatLng(23.7495801, 90.3772046);
    private static final LatLng ut_bir_utta = new LatLng(23.7430083, 90.4126245);
    private static final LatLng ut_hatkola = new LatLng(23.7177457, 90.4213536);
    private static final LatLng ut_tipu = new LatLng(23.7152777, 90.4140768);
    private static final LatLng ut_lal = new LatLng(23.7133131, 90.4141277);
    private static final LatLng ut_nawabpur = new LatLng(23.7219939, 90.4117164);
    private static final LatLng ut_begum_bazar = new LatLng(23.7165104, 90.3974122);
    private static final LatLng ut_vip = new LatLng(23.7356253, 90.4157477);
    private static final LatLng ut_bangla = new LatLng(23.7062416, 90.4115756);
    private static final LatLng ut_new = new LatLng(23.7411581, 90.4121305);
    private static final LatLng ut_kamla = new LatLng(23.7289113, 90.4344956);
    private static final LatLng ut_johnson = new LatLng(23.7135562, 90.4091844);
    private static final LatLng ut_azim = new LatLng(23.7239323, 90.3871308);
    private static final LatLng ut_mir = new LatLng(23.7354758, 90.384409);
    //uttara bank atm
    private static final LatLng ut_bir_uttam = new LatLng(23.7770769, 90.4256009);
    private static final LatLng ut_shanti = new LatLng(23.7411581, 90.4121305);
    private static final LatLng ut_dar = new LatLng(23.797307, 90.353137);
    //Islami bank branch
    private static final LatLng i_gul = new LatLng(23.7887842, 90.4069815);
    private static final LatLng i_bir = new LatLng(23.7769284, 90.4255942);
    private static final LatLng i_bir_uttam = new LatLng(23.7802611, 90.4182569);
    private static final LatLng i_new = new LatLng(23.7444826, 90.4144442);
    private static final LatLng i_new_esk = new LatLng(23.7485209, 90.4026144);
    private static final LatLng i_pragati = new LatLng(23.8113293, 90.421131);
    private static final LatLng i_kachu = new LatLng(23.7908953, 90.3878018);
    private static final LatLng i_begum = new LatLng(23.8177729, 90.3656688);
    private static final LatLng i_idb = new LatLng(23.7781809, 90.3774691);
    private static final LatLng i_kushal = new LatLng(23.8649261, 90.3996706);
    private static final LatLng i_mazar = new LatLng(23.7972333, 90.3531342);
    //islami bank atm
    private static final LatLng i_gulshan = new LatLng(23.7887842, 90.4069815);
    private static final LatLng i_bir_uttam_ave = new LatLng(23.7810029, 90.4254802);
    private static final LatLng i_eskar = new LatLng(23.7485209, 90.4026144);
    private static final LatLng i_kazi = new LatLng(23.7593244, 90.3898725);
    private static final LatLng i_bashud = new LatLng(23.812826, 90.4261611);
    private static final LatLng i_manikdi = new LatLng(23.816041, 90.40872);
    private static final LatLng i_mir = new LatLng(23.8065343, 90.367645);
    private static final LatLng i_shahedd = new LatLng(23.7760227, 90.3960657);
    private static final LatLng i_mazar_road = new LatLng(23.7972333, 90.3531342);
    private static final LatLng i_dhk1 = new LatLng(23.8209948, 90.3650138);
    //Jamuna bank branch
    private static final LatLng j_dhk1 = new LatLng(23.7432783, 90.3820686);
    private static final LatLng j_midas = new LatLng(23.7288449, 90.4170252);
    private static final LatLng j_moti = new LatLng(23.7279215, 90.4173973);
    private static final LatLng j_dhk2 = new LatLng(23.7264303, 90.4186446);
    private static final LatLng j_topkhana = new LatLng(23.7306054, 90.4138113);
    private static final LatLng j_chameli = new LatLng(23.7411534, 90.4126257);
    private static final LatLng j_khales = new LatLng(23.7173383, 90.4085009);
    private static final LatLng j_bangshal = new LatLng(23.769853, 90.3586472);
    private static final LatLng j_dar = new LatLng(23.7963719, 90.3536278);
    private static final LatLng j_begum = new LatLng(23.8045414, 90.3698445);
    private static final LatLng j_ismail = new LatLng(23.7877059, 90.3881559);
    private static final LatLng j_hazi = new LatLng(23.8172382, 90.4208841);
    private static final LatLng j_sector = new LatLng(23.8760174, 90.4038455);
    private static final LatLng j_airport = new LatLng(23.8516487, 90.4151145);
    //jamuna bank atm
    private static final LatLng j_sonar = new LatLng(23.7422815, 90.3920306);
    private static final LatLng j_ground = new LatLng(23.754703, 90.3915864);
    private static final LatLng j_dil = new LatLng(23.7288449, 90.4170252);
    private static final LatLng j_dit = new LatLng(23.7335935, 90.4159596);
    private static final LatLng j_bir_ut = new LatLng(23.7377602, 90.4080284);
    private static final LatLng j_ground_floor = new LatLng(23.7411657, 90.4204014);
    private static final LatLng j_cir = new LatLng(23.7266324, 90.4253286);
    private static final LatLng j_rankin = new LatLng(23.7154275, 90.4163218);
    private static final LatLng j_dhk3 = new LatLng(23.7173383, 90.4085009);
    private static final LatLng j_mirp = new LatLng(23.7785214, 90.3604352);
    private static final LatLng j_dar1 = new LatLng(23.7945239, 90.3532711);
    private static final LatLng j_milk = new LatLng(23.8068498, 90.362069);
    private static final LatLng j_s_kafrul = new LatLng(23.7877059, 90.3881559);
    private static final LatLng j_mohak = new LatLng(23.7809416, 90.4057619);
    private static final LatLng j_gul1 = new LatLng(23.7806544, 90.4169118);
    private static final LatLng j_gausul = new LatLng(23.8692652, 90.3896458);
    private static final LatLng j_dhk_mym = new LatLng(23.9094849, 90.3978333);
    private static final LatLng j_sat = new LatLng(23.7566646, 90.3621586);
    private static final LatLng j_atish = new LatLng(23.7194081, 90.4291226);
    //national bank branch
    private static final LatLng na_samad = new LatLng(23.709514, 90.4342523);
    private static final LatLng na_shaheed = new LatLng(23.7494469, 90.4029697);
    private static final LatLng na_shahid = new LatLng(23.7542392, 90.4155346);
    private static final LatLng na_classic = new LatLng(23.7910378, 90.4016502);
    private static final LatLng na_uttara = new LatLng(23.7831984, 90.4257457);
    private static final LatLng na_spectra = new LatLng(23.7372127, 90.3866324);
    private static final LatLng na_quality = new LatLng(23.7053846, 90.4363151);
    private static final LatLng na_nazrul = new LatLng(23.7456034, 90.3713468);
    private static final LatLng na_dhk1 = new LatLng(23.814346, 90.4083348);
    private static final LatLng na_toyenbee = new LatLng(23.7249866, 90.4219289);
    private static final LatLng na_fahad = new LatLng(23.807092, 90.3692877);
    private static final LatLng na_motij = new LatLng(23.7300651, 90.415847);
    private static final LatLng na_nawab = new LatLng(23.7219275, 90.4119551);
    private static final LatLng na_babu = new LatLng(23.7823345, 90.398533);
    private static final LatLng na_dhk_mawa = new LatLng(23.7041436, 90.4349061);
    private static final LatLng na_bashu = new LatLng(23.8131562, 90.4289657);
    private static final LatLng na_kc = new LatLng(23.8605255, 90.422861);
    private static final LatLng na_bangshal = new LatLng(23.723739, 90.4162252);
    private static final LatLng na_pantha = new LatLng(23.7505176, 90.3886791);
    //national bank atm
    private static final LatLng na_gulsh = new LatLng(23.7845268, 90.4163768);
    private static final LatLng na_bbtoa = new LatLng(23.7780497, 90.3605149);
    private static final LatLng na_mirpu = new LatLng(23.7780259, 90.3604819);
    private static final LatLng na_ram = new LatLng(23.7499213, 90.3934407);
    private static final LatLng na_dhk2 = new LatLng(23.8105828, 90.3608208);
    private static final LatLng na_school = new LatLng(23.7542392, 90.4155346);
    // This is where the View is being Created!!!

    public void processMap(final View v) {
        if (mMap == null) {
            mMap = ((MapView) v.findViewById(R.id.mapView)).getMap();
            mMap.setOnMyLocationChangeListener(myLocationChangeListener);
        }
        if (mMap != null) {
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    if (position == 0) {
                        //brac bank
                        //if (marker == null) {
                        mMap.clear();
                        spinner2.setSelection(adapter2.getCount());
                        marker = mMap.addMarker(new MarkerOptions().position(b_gul).title("Brac Bank\nGulshan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_gul, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_ngul).title("Brac Bank\nNorth Gulshan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_ngul, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_banshundhara).title("Brac Bank\nBashundhara Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_banshundhara, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_asad).title("Brac Bank\nAsad Gate Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_asad, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_banani).title("Brac Bank\nBanani Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_banani, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_ashkona).title("Brac Bank\nAshkona Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_ashkona, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_samasjid).title("Brac Bank\nSatmosjod Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_samasjid, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_elephant).title("Brac Bank\nElephant Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_elephant, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_eskaton).title("Brac Bank\nEskaton").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_eskaton, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_mirpur).title("Brac Bank\nMirpur Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_mirpur, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_begum).title("Brac Bank\nBegum Rokeya Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_begum, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_mogbazar).title("Brac Bank\nMogbazar Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_mogbazar, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_motijheel).title("Brac Bank\nMotijheel Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_motijheel, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_notun).title("Brac Bank\nNatun Bazar Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_notun, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_banasree).title("Brac Bank\nBanasree").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_banasree, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_shymoli).title("Brac Bank\nShymoli Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_shymoli, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_dakk).title("Brac Bank\nDakkhin Khan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_dakk, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_shantinagar).title("Brac Bank\nShantinagar Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_shantinagar, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_uttra).title("Brac Bank\nUttara Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_uttra, 11));
                        //atm
                        marker = mMap.addMarker(new MarkerOptions().position(b_kamal).title("Brac Bank ATM\nKamal Ataturk Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_kamal, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_banani_1).title("Brac Bank ATM\nBanani").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_banani_1, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_agargoan).title("Brac Bank ATM\nAgargaon").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_agargoan, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_farm).title("Brac Bank ATM\nFarmgate").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_farm, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_azim).title("Brac Bank ATM\nAzimpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_azim, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_gulatm).title("Brac Bank ATM\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_gulatm, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_prag).title("Brac Bank ATM\nPragati Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_prag, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_kac).title("Brac Bank ATM\nKachukhet").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_kac, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_khilkhet).title("Brac Bank ATM\nKhilkhet").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_khilkhet, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_bir).title("Brac Bank ATM\nBir Uttam Rafiqul Islam Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_bir, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_air).title("Brac Bank ATM\nAirport Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_air, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_ibra).title("Brac Bank ATM\nIbrahimpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_ibra, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_sha).title("Brac Bank ATM\nShaheed Tajuddin Ahmed Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_sha, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_sonar).title("Brac Bank ATM\nSonargaon").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_sonar, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_new).title("Brac Bank ATM\nNew Eskaton").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_new, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_khil).title("Brac Bank ATM\nKhilgaon Taltola").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_khil, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_aftab).title("Brac Bank ATM\nAftab Nagar").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_aftab, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_kazi).title("Brac Bank ATM\nKazi Nazrul Islam Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_kazi, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_pant).title("Brac Bank ATM\nPanthapath").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_pant, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_begum_rokeya).title("Brac Bank ATM\nBegum Rokeya Avenue").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_begum_rokeya, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_moti).title("Brac Bank ATM\nMotijheel").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_moti, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_indi).title("Brac Bank ATM\nIndira Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_indi, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(b_rampura).title("Brac Bank ATM\nRampura DIT Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_rampura, 11));
                        //}
                    } else if (position == 1) {
                        //city bank
                        //if (marker != null) {
                        mMap.clear();
                        spinner2.setSelection(adapter2.getCount());
                        marker = mMap.addMarker(new MarkerOptions().position(c_dil).title("City Bank\nDilkhusha Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_dil, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_banani).title("City Bank\nBanani Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_banani, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_dhan).title("City Bank\nDhanmondi 27 Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_dhan, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_kar).title("City Bank\nKarwanbazar Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_kar, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_mirpu).title("City Bank\nMirpur Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_mirpu, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_mog).title("City Bank\nMogbazar Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_mog, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_moti).title("City Bank\nMotijheel Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_moti, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_kakra).title("City Bank\nKakrail Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_kakra, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_dhan_2).title("City Bank\nDhanmondi 2 Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_dhan_2, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_pall).title("City Bank\nPallabi Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_pall, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_bad).title("City Bank\nBadda Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_bad, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_shy).title("City Bank\nShymoli Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_shy, 11));
                        //atm
                        marker = mMap.addMarker(new MarkerOptions().position(c_dilkhusa).title("City Bank ATM\nDilkhusha").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_dilkhusa, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_rif).title("City Bank ATM\nRiffle Squre").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_rif, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_pant).title("City Bank ATM\nPanthapath").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_pant, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_gre).title("City Bank ATM\nGreen Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_gre, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_wari).title("City Bank ATM\nWari").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_wari, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_ban).title("City Bank ATM\nBanani").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_ban, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_moh).title("City Bank ATM\nMohakhali").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_moh, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_baily).title("City Bank ATM\nBaily Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_baily, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_sat).title("City Bank ATM\nSatmosjid Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_sat, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_moni).title("City Bank ATM\nMonipuripara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_moni, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_pal).title("City Bank ATM\nPallabi").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_pal, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_mogb).title("City Bank ATM\nMogbazar").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_mogb, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_rok).title("City Bank ATM\nRokeya Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_rok, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_bana).title("City Bank ATM\nBanasree").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_bana, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_khil).title("City Bank ATM\nKhilgaon").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_khil, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_gul).title("City Bank ATM\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_gul, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_utt).title("City Bank ATM\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_utt, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_seg).title("City Bank ATM\nSegun Bagicha").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_seg, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_mir).title("City Bank ATM\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_mir, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_west).title("City Bank ATM\nRampura").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_west, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_dar).title("City Bank ATM\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_dar, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_bash).title("City Bank ATM\nBashundhara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_bash, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_fak).title("City Bank ATM\nFakirapool").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_fak, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_kam).title("City Bank ATM\nKamlapur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_kam, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_jan).title("City Bank ATM\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_jan, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_ele).title("City Bank ATM\nElephant Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_ele, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_shah).title("City Bank ATM\nShahjadpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_shah, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_dhanmondi).title("City Bank ATM\nDhanmondi").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_dhanmondi, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_uttara).title("City Bank ATM\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_uttara, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_mirpur).title("City Bank ATM\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_mirpur, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_elephant).title("City Bank ATM\nNew Elephant Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_elephant, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_mirpur_rup).title("City Bank ATM\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_mirpur_rup, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_kak).title("City Bank ATM\nKakrail").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_kak, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_dhaka).title("City Bank ATM\nDhaka Stoke Exchange").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_dhaka, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_rabi).title("City Bank ATM\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_rabi, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_prag).title("City Bank ATM\nPragati Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_prag, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_merul).title("City Bank ATM\nMerul Badda").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_merul, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(c_nik).title("City Bank ATM\nNikunja").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_nik, 11));
                        //}
                    } else if (position == 2) {
                        //United
                        //if (marker != null) {
                        mMap.clear();
                        spinner2.setSelection(adapter2.getCount());
                        marker = mMap.addMarker(new MarkerOptions().position(u_ban).title("United Commercial Bank\nBanani Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_ban, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(u_mad).title("United Commercial Bank\nMadani Avenue Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_mad, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(u_gul).title("United Commercial Bank\nGulshan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_gul, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(u_bas).title("United Commercial Bank\nBashundhara Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_bas, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(u_dhan).title("United Commercial Bank\nDhanmondi 27 Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_dhan, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(u_kat).title("United Commercial Bank\nKatabon Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_kat, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(u_gulshan).title("United Commercial Bank\nGulshan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_gulshan, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(u_kar).title("United Commercial Bank\nKarwanbazar Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_kar, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(u_esk).title("United Commercial Bank\nEskaton Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_esk, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(u_aftab).title("United Commercial Bank\nAftabnagar Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_aftab, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(u_sidd).title("United Commercial Bank\nSiddeswari Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_sidd, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(u_sha).title("United Commercial Bank\nShahid syed Nazrul Islam Avenue Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_sha, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(u_moti).title("United Commercial Bank\nMotijheel Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_moti, 11));
                        //atm
                        marker = mMap.addMarker(new MarkerOptions().position(u_bash).title("United Commercial ATM\nBashundhara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_bash, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(u_gus).title("United Commercial ATM\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_gus, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(u_ring).title("United Commercial ATM\nRing Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_ring, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(u_asad).title("United Commercial ATM\nAsad Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_asad, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(u_kal).title("United Commercial ATM\nKalabagan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_kal, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(u_dhanmo).title("United Commercial ATM\nDhanmondi").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_dhanmo, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(u_bir).title("United Commercial ATM\nBir Uttam Mir Shakwat Ali Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_bir, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(u_lal).title("United Commercial ATM\nLalbagh").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_lal, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(u_pant).title("United Commercial ATM\nPanthapath").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_pant, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(u_new).title("United Commercial ATM\nNew Elephant Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_new, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(u_motijheel).title("United Commercial ATM\nMotijheel").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_motijheel, 11));
                        //}
                    } else if (position == 3) {
                        //dutch bangla
                        //if (marker != null) {
                        mMap.clear();
                        spinner2.setSelection(adapter2.getCount());
                        marker = mMap.addMarker(new MarkerOptions().position(d_sonar).title("Dutch Bangla Bank\nSonargaon Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_sonar, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(d_dit).title("Dutch Bangla Bank\nDIT Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_dit, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(d_kazi).title("Dutch Bangla Bank\nKazi nazrul Islam Avenue Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_kazi, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(d_shanti).title("Dutch Bangla Bank\nShanti Nagar Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_shanti, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(d_ash).title("Dutch Bangla Bank\nAshkona Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_ash, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(d_khil).title("Dutch Bangla Bank\nKhilkhet Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_khil, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(d_ring).title("Dutch Bangla Bank\nRing Road Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_ring, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(d_ele).title("Dutch Bangla Bank\nElephant Road Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_ele, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(d_dil).title("Dutch Bangla Bank\nDilkhusha Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_dil, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(d_sat).title("Dutch Bangla Bank\nSatmosjid Road Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_sat, 11));
                        //atm
                        marker = mMap.addMarker(new MarkerOptions().position(d_cant).title("Dutch Bangla Bank ATM\nCantonment").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_cant, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(d_utt).title("Dutch Bangla Bank ATM\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_utt, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(d_vas).title("Dutch Bangla Bank ATM\nVashantek").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_vas, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(d_sha).title("Dutch Bangla Bank ATM\nShahid latif Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_sha, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(d_shah).title("Dutch Bangla Bank ATM\nShah Kabir Mazar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_shah, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(d_air).title("Dutch Bangla Bank ATM\nAirport").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_air, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(d_ashkona).title("Dutch Bangla Bank ATM\nAshkona").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_ashkona, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(d_gau).title("Dutch Bangla Bank ATM\nGausul Azam Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_gau, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(d_sonargoan).title("Dutch Bangla Bank ATM\nSonargaon").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_sonargoan, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(d_taj).title("Dutch Bangla Bank ATM\nTejgaon").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_taj, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(d_ram).title("Dutch Bangla Bank ATM\nRampura").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_ram, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(d_banasree).title("Dutch Bangla Bank ATM\nBanasree").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_banasree, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(d_khilkhet).title("Dutch Bangla Bank ATM\nKhilgaon").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_khilkhet, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(d_new).title("Dutch Bangla Bank ATM\nNew Eskaton Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_new, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(d_karwan).title("Dutch Bangla Bank ATM\nKarwanbazar").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_karwan, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(d_shantinagar).title("Dutch Bangla Bank ATM\nShantinagar").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_shantinagar, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(d_baily).title("Dutch Bangla Bank ATM\nBaily Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_baily, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(d_shahbag).title("Dutch Bangla Bank ATM\nShahbag").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_shahbag, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(d_moti).title("Dutch Bangla Bank ATM\nMotijheel").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_moti, 11));
                        //}
                    } else if (position == 4) {
                        //hsbc
                        //if (marker != null) {
                        mMap.clear();
                        spinner2.setSelection(adapter2.getCount());
                        marker = mMap.addMarker(new MarkerOptions().position(h_dha).title("HSBC Bank\nDhanmondi Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(h_dha, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(h_gul).title("HSBC Bank\nGulshan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(h_gul, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(h_mir).title("HSBC Bank\nMirpur Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(h_mir, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(h_sonar).title("HSBC Bank\nSonargoan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(h_sonar, 11));
                        //atm
                        marker = mMap.addMarker(new MarkerOptions().position(h_ram).title("HSBC Bank ATM\nRampura").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(h_ram, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(h_gul2).title("HSBC Bank ATM\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(h_gul2, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(h_gul1).title("HSBC Bank ATM\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(h_gul1, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(h_bash).title("HSBC Bank ATM\nBashundhara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(h_bash, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(h_chak).title("HSBC Bank ATM\nChawbazar").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(h_chak, 11));
                        //}
                    } else if (position == 5) {
                        //standard
                        //if (marker != null) {
                        mMap.clear();
                        spinner2.setSelection(adapter2.getCount());
                        marker = mMap.addMarker(new MarkerOptions().position(s_gul).title("Standard Chartered Bank\nGulshan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_gul, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(s_satm).title("Standard Chartered Bank\nSatmosjid Road Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_satm, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(s_dhan).title("Standard Chartered Bank\nDhanmondi Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_dhan, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(s_beg).title("Standard Chartered Bank\nBegum Rokeya Avenue Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_beg, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(s_banasree).title("Standard Chartered Bank\nBanasree Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_banasree, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(s_chaw).title("Standard Chartered Bank\nChawbazar Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_chaw, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(s_khil).title("Standard Chartered Bank\nKhilgaon Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_khil, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(s_utt).title("Standard Chartered Bank\nUttara Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_utt, 11));
                        //atm
                        marker = mMap.addMarker(new MarkerOptions().position(s_ring).title("Standard Chartered ATM\nRing Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_ring, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(s_begum).title("Standard Chartered ATM\nBegum Rokeya Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_begum, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(s_mog).title("Standard Chartered ATM\nMogbazar").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_mog, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(s_khilkhet).title("Standard Chartered ATM\nKhilkhet").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_khilkhet, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(s_bangla).title("Standard Chartered ATM\nBangla Academy Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_bangla, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(s_lal).title("Standard Chartered ATM\nLalbagh").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_lal, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(s_banani).title("Standard Chartered ATM\nBanani").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_banani, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(s_sat).title("Standard Chartered ATM\nSatmosjid Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_sat, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(s_dhanmon).title("Standard Chartered ATM\nDhanmondi").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_dhanmon, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(s_gulshan).title("Standard Chartered ATM\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_gulshan, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(s_gul1).title("Standard Chartered ATM\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_gul1, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(s_farm).title("Standard Chartered ATM\nFarmgate").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_farm, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(s_bashu).title("Standard Chartered ATM\nBashundhara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_bashu, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(s_agar).title("Standard Chartered ATM\nAgargaon").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_agar, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(s_dit).title("Standard Chartered ATM\nDIT Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_dit, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(s_gre).title("Standard Chartered ATM\nGreen Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_gre, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(s_new).title("Standard Chartered ATM\nNew Baily Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_new, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(s_segu).title("Standard Chartered ATM\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_segu, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(s_uttara).title("Standard Chartered ATM\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_uttara, 11));
                        //}
                    } else if (position == 6) {
                        //prime
                        //if (marker != null) {
                        mMap.clear();
                        spinner2.setSelection(adapter2.getCount());
                        marker = mMap.addMarker(new MarkerOptions().position(p_ban).title("Prime Bank\nBanani Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_ban, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_gul).title("Prime Bank\nGulshan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_gul, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_bas).title("Prime Bank\nBashundhara Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_bas, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_kach).title("Prime Bank\nKachukhet Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_kach, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_moh).title("Prime Bank\nMohakhali Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_moh, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_tej).title("Prime Bank\nTejgaon Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_tej, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_tejturi).title("Prime Bank\nTejturi Bazar Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_tejturi, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_mid).title("Prime Bank\nMiddle Badda Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_mid, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_dhanmond).title("Prime Bank\nDhanmondi Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_dhanmond, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_asad).title("Prime Bank\nAsad Avenue Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_asad, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_sat).title("Prime Bank\nSatmosjid Road Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_sat, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_new).title("Prime Bank\nNew Eskaton Road Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_new, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_ele).title("Prime Bank\nElephant Road Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_ele, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_beg).title("Prime Bank\nBegum Rokeya Sarani Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_beg, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_banasree).title("Prime Bank\nBanasree Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_banasree, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_ring).title("Prime Bank\nRing Road Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_ring, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_mali).title("Prime Bank\nMalibag Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_mali, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_seg).title("Prime Bank\nSegun Bagicha Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_seg, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_mir).title("Prime Bank\nMirpur Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_mir, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_moti).title("Prime Bank\nMotijheel Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_moti, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_south).title("Prime Bank\nSouth Bashabo Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_south, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_dil).title("Prime Bank\nDilkhusha Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_dil, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_dit).title("Prime Bank\nDIT Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_dit, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_lal).title("Prime Bank\nLalbag Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_lal, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_utt).title("Prime Bank\nUttara Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_utt, 11));
                        //atm
                        marker = mMap.addMarker(new MarkerOptions().position(p_taj).title("Prime Bank ATM\nTajgaon").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_taj, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_bij).title("Prime Bank ATM\nBijoy Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_bij, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_pant).title("Prime Bank ATM\nPanthapath").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_pant, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_satmas).title("Prime Bank ATM\nSatmosjid Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_satmas, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_ram).title("Prime Bank ATM\nRampura").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_ram, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_elephant).title("Prime Bank ATM\nElephant Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_elephant, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_tajmahal).title("Prime Bank ATM\nTajmahal Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_tajmahal, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_shy).title("Prime Bank ATM\nShymoli").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_shy, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_dhanmondi).title("Prime Bank ATM\nDhanmondi").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_dhanmondi, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_begu).title("Prime Bank ATM\nBegum Rokeya Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_begu, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_ramna).title("Prime Bank ATM\nRamna").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_ramna, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_lalbag).title("Prime Bank ATM\nLalbagh").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_lalbag, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_shidd).title("Prime Bank ATM\nShiddeswari Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_shidd, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_tal).title("Prime Bank ATM\nTaltola").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_tal, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_begum).title("Prime Bank ATM\nBegum Rokeya Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_begum, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_rup).title("Prime Bank ATM\nRupnagar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_rup, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_dilkhusa).title("Prime Bank ATM\nDilkhusha").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_dilkhusa, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(p_uttara).title("Prime Bank ATM\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_uttara, 11));
                        //}
                    } else if (position == 7) {
                        //eastern
                        //if (marker != null) {
                        mMap.clear();
                        spinner2.setSelection(adapter2.getCount());
                        marker = mMap.addMarker(new MarkerOptions().position(e_mir).title("Eastern Bank\nMirpur Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_mir, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_mirpu).title("Eastern Bank\nMirpur Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_mirpu, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_ban).title("Eastern Bank\nBanani Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_ban, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_utt).title("Eastern Bank\nUttara Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_utt, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_ring).title("Eastern Bank\nRing Road Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_ring, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_dak).title("Eastern Bank\nDakshin Khan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_dak, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_uttara).title("Eastern Bank\nUttara Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_uttara, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_uttara_gr).title("Eastern Bank\nUttara Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_uttara_gr, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_kal).title("Eastern Bank\nKalabagan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_kal, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_bash).title("Eastern Bank\nBashundhara Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_bash, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_gul2).title("Eastern Bank\nGulshan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_gul2, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_gulave).title("Eastern Bank\nGulshan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_gulave, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_sonargoan).title("Eastern Bank\nSonargaon Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_sonargoan, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_gula).title("Eastern Bank\nGulshan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_gula, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_mog).title("Eastern Bank\nMogbazar Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_mog, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_shan).title("Eastern Bank\nShantinagar Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_shan, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_kak).title("Eastern Bank\nKakrail Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_kak, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_purana).title("Eastern Bank\nPurana Paltan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_purana, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_pil).title("Eastern Bank\nPilkhana Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_pil, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_shant).title("Eastern Bank\nShanti Nagar Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_shant, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_north).title("Eastern Bank\nNorth South Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_north, 11));
                        //atm
                        marker = mMap.addMarker(new MarkerOptions().position(e_rupnagar).title("Eastern Bank ATM\nRupnagar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_rupnagar, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_uttara).title("Eastern Bank ATM\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_uttara, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_moh).title("Eastern Bank ATM\nMohammadpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_moh, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_azam).title("Eastern Bank ATM\nAzampur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_azam, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_shmoli).title("Eastern Bank ATM\nShymoli").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_shmoli, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_moha).title("Eastern Bank ATM\nMohammadi Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_moha, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_tajmohol).title("Eastern Bank ATM\nTajmohol Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_tajmohol, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_gulshan).title("Eastern Bank ATM\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_gulshan, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_new).title("Eastern Bank ATM\nNew Airport Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_new, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_uttara_garib).title("Eastern Bank ATM\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_uttara_garib, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_azad).title("Eastern Bank ATM\nAsad Gate").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_azad, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_bijoy).title("Eastern Bank ATM\nBijoy Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_bijoy, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_mohakhali).title("Eastern Bank ATM\nMohakhali").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_mohakhali, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_farmgate).title("Eastern Bank ATM\nFarmgate").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_farmgate, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_shi).title("Eastern Bank ATM\nShimanto Square").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_shi, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_mirpur).title("Eastern Bank ATM\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_mirpur, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_karwan).title("Eastern Bank ATM\nKarwanbazar").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_karwan, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_elep).title("Eastern Bank ATM\nElephant Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_elep, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_gulshan_nav).title("Eastern Bank ATM\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_gulshan_nav, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_moni).title("Eastern Bank ATM\nMonipuripara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_moni, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_pant).title("Eastern Bank ATM\nPanthapath").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_pant, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_new_b).title("Eastern Bank ATM\nNew Baily Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_new_b, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_shidd).title("Eastern Bank ATM\nShiddheswari Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_shidd, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_shanr).title("Eastern Bank ATM\nShantinagar").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_shanr, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(e_dilkh).title("Eastern Bank ATM\nDilkhusha").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_dilkh, 11));
                        //}
                    } else if (position == 8) {
                        //AB bank
                        mMap.clear();
                        spinner2.setSelection(adapter2.getCount());
                        marker = mMap.addMarker(new MarkerOptions().position(ab_dhan).title("AB Bank Limited\nDhanmondi").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_dhan, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ab_gul).title("AB Bank Limited\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_gul, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ab_vip).title("AB Bank Limited\nVIP Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_vip, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ab_kazi).title("AB Bank Limited\nKazi Nazrul Islam Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_kazi, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ab_mali).title("AB Bank Limited\nMalibagh").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_mali, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ab_mir).title("AB Bank Limited\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_mir, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ab_dit).title("AB Bank Limited\nDIT Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_dit, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ab_moha).title("AB Bank Limited\nMohakhali").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_moha, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ab_bari).title("AB Bank Limited\nBaridhara").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_bari, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ab_she).title("AB Bank Limited\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_she, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ab_shymoli).title("AB Bank Limited\nShymoli").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_shymoli, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ab_uttara).title("AB Bank Limited\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_uttara, 11));
                        //atm
                        marker = mMap.addMarker(new MarkerOptions().position(ab_dhanmondi).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_dhanmondi, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ab_dhanmondi_1).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_dhanmondi_1, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ab_dhanmondi_2).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_dhanmondi_2, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ab_dhanmondi_3).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_dhanmondi_3, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ab_dhanmondi_4).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_dhanmondi_4, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ab_dhanmondi_5).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_dhanmondi_5, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ab_ban).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_ban, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ab_baridhara).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_baridhara, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ab_gulsh).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_gulsh, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ab_south_gul).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_south_gul, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ab_ring).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_ring, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ab_gulshan).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_gulshan, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ab_kak).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_kak, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ab_kazi_nazrul).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_kazi_nazrul, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ab_malibagh_1).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_malibagh_1, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ab_bashu).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_bashu, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ab_manik).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_manik, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ab_mirp).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_mirp, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ab_mir_1).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_mir_1, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ab_mir_2).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_mir_2, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ab_mir_rup).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_mir_rup, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ab_mir_sec).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_mir_sec, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ab_utt).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_utt, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ab_dak).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_dak, 11));
                    } else if (position == 9) {
                        //bank asia
                        mMap.clear();
                        spinner2.setSelection(adapter2.getCount());
                        marker = mMap.addMarker(new MarkerOptions().position(bn_bashu).title("Bank Asia\nBashundhara").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_bashu, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(bn_sonar).title("Bank Asia\nSonargoan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_sonar, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(bn_dak).title("Bank Asia\nDakshinkhan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_dak, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(bn_meher).title("Bank Asia\nMeher Plaza").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_meher, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(bn_ele).title("Bank Asia\nElephant Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_ele, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(bn_eska).title("Bank Asia\nEskaton").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_eska, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(bn_gul).title("Bank Asia\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_gul, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(bn_ban).title("Bank Asia\nBanani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_ban, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(bn_dil).title("Bank Asia\nDilkhusha").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_dil, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(bn_pallabi).title("Bank Asia\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_pallabi, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(bn_moha).title("Bank Asia\nMohakhali").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_moha, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(bn_pal).title("Bank Asia\nPurana Paltan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_pal, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(bn_moti).title("Bank Asia\nMotijheel").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_moti, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(bn_progati).title("Bank Asia\nProgoti Soroni").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_progati, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(bn_ring).title("Bank Asia\nRing Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_ring, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(bn_rup_mir).title("Bank Asia\nRupnagar Mirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_rup_mir, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(bn_bijoy).title("Bank Asia\nBijoy Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_bijoy, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(bn_cir).title("Bank Asia\nCircular Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_cir, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(bn_shymoli).title("Bank Asia\nShymoli").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_shymoli, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(bn_taj).title("Bank Asia\nTajgaon").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_taj, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(bn_utt).title("Bank Asia\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_utt, 11));
                        //atm
                        marker = mMap.addMarker(new MarkerOptions().position(bn_bashundhara).title("Bank Asia ATM\nBashundhara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_bashundhara, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(bn_eskaton).title("Bank Asia ATM\nEskaton").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_eskaton, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(bn_sonargaon).title("Bank Asia ATM\nSonargaon").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_sonargaon, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(bn_dhanmond).title("Bank Asia ATM\nDhanmondi").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_dhanmond, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(bn_dilku).title("Bank Asia ATM\nDilkhusha").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_dilku, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(bn_garden).title("Bank Asia ATM\nEskaton").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_garden, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(bn_bays).title("Bank Asia ATM\nBays Galleria").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_bays, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(bn_sat).title("Bank Asia ATM\nSatmosjid Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_sat, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(bn_mirpur).title("Bank Asia ATM\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_mirpur, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(bn_moham).title("Bank Asia ATM\nMohammadpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_moham, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(bn_kamla).title("Bank Asia ATM\nKamlapur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_kamla, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(bn_paltan).title("Bank Asia ATM\nPaltan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_paltan, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(bn_ring_road).title("Bank Asia ATM\nRing Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_ring_road, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(bn_shym).title("Bank Asia ATM\nShymoli").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_shym, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(bn_nazrul_avenue).title("Bank Asia ATM\nKazi Nazrul Islam Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_nazrul_avenue, 11));
                    } else if (position == 10) {
                        //exim bank
                        mMap.clear();
                        spinner2.setSelection(adapter2.getCount());
                        marker = mMap.addMarker(new MarkerOptions().position(ex_pragati).title("Exim Bank\nPragati Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_pragati, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ex_gul).title("Exim Bank\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_gul, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ex_pantha).title("Exim Bank\nPanthapath").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_pantha, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ex_bgmea).title("Exim Bank\nPnthapath").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_bgmea, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ex_gul2).title("Exim Bank\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_gul2, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ex_rokeya).title("Exim Bank\nBegum Rokeya Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_rokeya, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ex_dit).title("Exim Bank\nDIT Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_dit, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ex_pra).title("Exim Bank\nPragati Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_pra, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ex_ele).title("Exim Bank\nElephant Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_ele, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ex_moti).title("Exim Bank\nMotijheel").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_moti, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ex_sat).title("Exim Bank\nSatmosjid Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_sat, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ex_rokeya_sarani).title("Exim Bank\nBegum Rokeya Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_rokeya_sarani, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ex_ring).title("Exim Bank\nRing Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_ring, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ex_nawabpur).title("Exim Bank\nNawabpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_nawabpur, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ex_garib).title("Exim Bank\nGarib-e-Newaz").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_garib, 11));
                        //atm
                        marker = mMap.addMarker(new MarkerOptions().position(ex_dhk).title("Exim Bank ATM\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_dhk, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ex_ring_road).title("Exim Bank ATM\nRing Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_ring_road, 11));
                    } else if (position == 11) {
                        //marcentail bank
                        mMap.clear();
                        spinner2.setSelection(adapter2.getCount());
                        marker = mMap.addMarker(new MarkerOptions().position(m_pragati).title("Mercantile Bank\nPragati Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_pragati, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(m_kachu).title("Mercantile Bank\nKachukhet").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_kachu, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(m_rokeya).title("Mercantile Bank\nBegum Rokeya Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_rokeya, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(m_gul).title("Mercantile Bank\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_gul, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(m_moha).title("Mercantile Bank\nMohakhali").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_moha, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(m_moha_bir).title("Mercantile Bank\nMohakhali").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_moha_bir, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(m_ring).title("Mercantile Bank\nRing Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_ring, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(m_dit).title("Mercantile Bank\nDIT Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_dit, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(m_dhaka).title("Mercantile Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_dhaka, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(m_pantha).title("Mercantile Bank\nPanthapath").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_pantha, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(m_kazi).title("Mercantile Bank\nKazi Nazrul Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_kazi, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(m_sat).title("Mercantile Bank\nSatmosjid Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_sat, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(m_cir).title("Mercantile Bank\nCirculer Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_cir, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(m_jaha).title("Mercantile Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_jaha, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(m_moulana).title("Mercantile Bank\nMoulana Vasani Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_moulana, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(m_sharani).title("Mercantile Bank\nShahid Syed Nazrul Islam Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_sharani, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(m_malek).title("Mercantile Bank\nMalek Mansion").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_malek, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(m_pass).title("Mercantile Bank\nPresident Pass").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_pass, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(m_moulo).title("Mercantile Bank\nMoulovibazar Dhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_moulo, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(m_lal).title("Mercantile Bank\nLal Mohan Shaha Street").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_lal, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(m_indira).title("Mercantile Bank\nIndira Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_indira, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(m_asad).title("Mercantile Bank\nAsad Gate").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_asad, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(m_manikdi).title("Mercantile Bank\nManikdi Bazar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_manikdi, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(m_ibra).title("Mercantile Bank\nIbrahimpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_ibra, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(m_shahid).title("Mercantile Bank\nShahid Latif Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_shahid, 11));
                        //atm
                        marker = mMap.addMarker(new MarkerOptions().position(m_mazar).title("Mercantile Bank ATM\nMazar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_mazar, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(m_dhk).title("Mercantile Bank ATM\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_dhk, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(m_haka).title("Mercantile Bank ATM\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_haka, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(m_shezan).title("Mercantile Bank ATM\nShezan Point").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_shezan, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(m_shahid_baki).title("Mercantile Bank ATM\nShahid Baki Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_shahid_baki, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(m_atish).title("Mercantile Bank ATM\nAtish Diponkar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_atish, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(m_purana_paltan).title("Mercantile Bank ATM\nPurana paltan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_purana_paltan, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(m_dit_1).title("Mercantile Bank ATM\nDIT Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_dit_1, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(m_north_kamla).title("Mercantile Bank ATM\nNorth Kamlapur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_north_kamla, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(m_pilkhana).title("Mercantile Bank ATM\nPilkhana").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_pilkhana, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(m_rankin).title("Mercantile Bank ATM\nRankin Street").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_rankin, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(m_vasani).title("Mercantile Bank ATM\nMoulana Vasani Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_vasani, 11));

                    } else if (position == 12) {
                        mMap.clear();
                        spinner2.setSelection(adapter2.getCount());
                        marker = mMap.addMarker(new MarkerOptions().position(mu_pragati).title("Mutual Trust Bank\nPragati Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_pragati, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_dhk).title("Mutual Trust Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_dhk, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_dhk1).title("Mutual Trust Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_dhk1, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_gul).title("Mutual Trust Bank\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_gul, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_mir).title("Mutual Trust Bank\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_mir, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_dhk2).title("Mutual Trust Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_dhk2, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_bir).title("Mutual Trust Bank\nBir Uttam Rafiqul Islam Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_bir, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_gulshan).title("Mutual Trust Bank\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_gulshan, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_dhk3).title("Mutual Trust Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_dhk3, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_bir_uttam).title("Mutual Trust Bank\nBir Uttam Mir Shawkat Sarak").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_bir_uttam, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_service).title("Mutual Trust Bank\nService Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_service, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_dhk4).title("Mutual Trust Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_dhk4, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_shah).title("Mutual Trust Bank\nShah Mokhdum Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_shah, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_moha).title("Mutual Trust Bank\nMohammadpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_moha, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_hatir).title("Mutual Trust Bank\nHatirjheel").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_hatir, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_chandra).title("Mutual Trust Bank\nChandrashila Suvastu Tower").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_chandra, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_dhk5).title("Mutual Trust Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_dhk5, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_ele).title("Mutual Trust Bank\nElephant Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_ele, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_uttara).title("Mutual Trust Bank\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_uttara, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_samsul).title("Mutual Trust Bank\nBir Uttam Samsul Alam Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_samsul, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_central).title("Mutual Trust Bank\nCentral Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_central, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_moti).title("Mutual Trust Bank\nMotijheel").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_moti, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_ramna).title("Mutual Trust Bank\nRamna").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_ramna, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_dil).title("Mutual Trust Bank\nDilkhusha").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_dil, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_motijheel).title("Mutual Trust Bank\nMotijheel").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_motijheel, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_kazi).title("Mutual Trust Bank\nKazi Nazrul Islam Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_kazi, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_chowk).title("Mutual Trust Bank\nLalbag").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_chowk, 11));
                        //atm
                        marker = mMap.addMarker(new MarkerOptions().position(mu_dhk6).title("Mutual Trust Bank ATM\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_dhk6, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_dit).title("Mutual Trust Bank ATM\nDIT Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_dit, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_kemal).title("Mutual Trust Bank ATM\nKemal Ataturk Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_kemal, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_dhk7).title("Mutual Trust Bank ATM\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_dhk7, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_terminal).title("Mutual Trust Bank ATM\nTerminal").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_terminal, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_dhk8).title("Mutual Trust Bank ATM\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_dhk8, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_jashi).title("Mutual Trust Bank ATM\nJashiuddin Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_jashi, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_sonar).title("Mutual Trust Bank ATM\nSoanrgaon").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_sonar, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_dhk9).title("Mutual Trust Bank ATM\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_dhk9, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_abc).title("Mutual Trust Bank ATM\nABC Heritage").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_abc, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_pantha).title("Mutual Trust Bank ATM\nPanthapath").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_pantha, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_esk).title("Mutual Trust Bank ATM\nEskaton").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_esk, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_dit_road).title("Mutual Trust Bank ATM\nDIT Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_dit_road, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_kata).title("Mutual Trust Bank ATM\nKatabon").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_kata, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_toyen).title("Mutual Trust Bank ATM\nToyenbee Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_toyen, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(mu_hatkola).title("Mutual Trust Bank ATM\nHotkola Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_hatkola, 11));

                    } else if (position == 13) {
                        mMap.clear();
                        spinner2.setSelection(adapter2.getCount());
                        marker = mMap.addMarker(new MarkerOptions().position(pr_gul).title("The Premier Bank\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pr_gul, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(pr_kemal).title("The Premier Bank\nKemal Ataturk Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pr_kemal, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(pr_dhk1).title("The Premier Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pr_dhk1, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(pr_bir).title("The Premier Bank\nBir Uttam AK Khandakar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pr_bir, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(pr_mir).title("The Premier Bank\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pr_mir, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(pr_begum).title("The Premier Bank\nBegum Rokeya Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pr_begum, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(pr_agrani).title("The Premier Bank\nAgrani Midtown Complex").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pr_agrani, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(pr_ring).title("The Premier Bank\nRing Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pr_ring, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(pr_new).title("The Premier Bank\nNew Market").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pr_new, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(pr_vip).title("The Premier Bank\nVIP Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pr_vip, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(pr_air).title("The Premier Bank\nAirport").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pr_air, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(pr_dhk2).title("The Premier Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pr_dhk2, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(pr_momen).title("The Premier Bank\nMomenbug").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pr_momen, 11));
                        //atm
                    } else if (position == 14) {
                        mMap.clear();
                        spinner2.setSelection(adapter2.getCount());
                        marker = mMap.addMarker(new MarkerOptions().position(o_kak).title("One Bank\nKakrail").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(o_kak, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(o_alam).title("One Bank\nAlam Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(o_alam, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(o_lal).title("One Bank\nLalbagh").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(o_lal, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(o_al).title("One Bank\nAL Kauser Palace").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(o_al, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(o_selina).title("One Bank\nShahid Shagbadik Selina Parvin Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(o_selina, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(o_dhk1).title("One Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(o_dhk1, 11));
                        //atm
                        marker = mMap.addMarker(new MarkerOptions().position(o_dil).title("One Bank ATM\nDilkhusha").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(o_dil, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(o_kawran).title("One Bank ATM\nKawran Bazar").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(o_kawran, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(o_lalbag).title("One Bank ATM\nLalbag").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(o_lalbag, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(o_dak).title("One Bank ATM\nDakshin Basabo").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(o_dak, 11));
                    } else if (position == 15) {
                        mMap.clear();
                        spinner2.setSelection(adapter2.getCount());
                        marker = mMap.addMarker(new MarkerOptions().position(se_simpl).title("SouthEast Bank\nSimpletree").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_simpl, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(se_begum).title("SouthEast Bank\nBegum Rokeya Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_begum, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(se_dhk1).title("SouthEast Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_dhk1, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(se_bir).title("SouthEast Bank\nBir Uttam AK Khandakar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_bir, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(se_gul).title("SouthEast Bank\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_gul, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(se_islampur).title("SouthEast Bank\nIslampur").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_islampur, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(se_eska).title("SouthEast Bank\nEskaton").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_eska, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(se_mir).title("SouthEast Bank\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_mir, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(se_dhk2).title("SouthEast Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_dhk2, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(se_ele).title("SouthEast Bank\nElephant Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_ele, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(se_rafi).title("SouthEast Bank\nBir Uttam Rafiqul Islam Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_rafi, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(se_bana).title("SouthEast Bank\nBanasree").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_bana, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(se_mog).title("SouthEast Bank\nMogbazar").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_mog, 11));
                        //atm
                        marker = mMap.addMarker(new MarkerOptions().position(se_rangan).title("SouthEast Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_rangan, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(se_moti).title("SouthEast Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_moti, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(se_dhk3).title("SouthEast Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_dhk3, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(se_shahid).title("SouthEast Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_shahid, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(se_north).title("SouthEast Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_north, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(se_purabi).title("SouthEast Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_purabi, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(se_dhk4).title("SouthEast Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_dhk4, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(se_panthapath).title("SouthEast Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_panthapath, 11));

                    } else if (position == 16) {
                        mMap.clear();
                        spinner2.setSelection(adapter2.getCount());
                        marker = mMap.addMarker(new MarkerOptions().position(hk_dhk1).title("The HongKong and Shanghai Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hk_dhk1, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(hk_dhk2).title("The HongKong and Shanghai Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hk_dhk2, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(hk_begum).title("The HongKong and Shanghai Bank\nBegum Rokeya Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hk_begum, 11));
                        //atm
                        marker = mMap.addMarker(new MarkerOptions().position(hk_dhk3).title("The HongKong and Shanghai Bank ATM\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hk_dhk3, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(hk_gul).title("The HongKong and Shanghai Bank ATM\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hk_gul, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(hk_bir).title("The HongKong and Shanghai Bank ATM\nBir Uttam Mir Shakhawat Sarak").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hk_bir, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(hk_bir_uttam).title("The HongKong and Shanghai Bank ATM\nBir Uttam CR Datta Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hk_bir_uttam, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(hk_bashu).title("The HongKong and Shanghai Bank ATM\nBashundhara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hk_bashu, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(hk_water).title("The HongKong and Shanghai Bank ATM\nWater Works Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hk_water, 11));
                    } else if (position == 17) {
                        mMap.clear();
                        spinner2.setSelection(adapter2.getCount());
                        marker = mMap.addMarker(new MarkerOptions().position(st_gul).title("Standard Bank\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(st_gul, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(st_bir).title("Standard Bank\nBir Uttam AK Khandakar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(st_bir, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(st_pragati).title("Standard Bank\nPragati Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(st_pragati, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(st_moha).title("Standard Bank\nMohammadpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(st_moha, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(st_dhan).title("Standard Bank\nDhanmondi").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(st_dhan, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(st_lions).title("Standard Bank\nNorthern Lions").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(st_lions, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(st_dhk1).title("Standard Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(st_dhk1, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(st_cresent).title("Standard Bank\nTopkhana").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(st_cresent, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(st_moti).title("Standard Bank\nMotijheel").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(st_moti, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(st_air).title("Standard Bank\nAirport").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(st_air, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(st_shahi).title("Standard Bank\nShahi Bhaban").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(st_shahi, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(st_momen).title("Standard Bank\nMomenbug Chowrasta").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(st_momen, 11));
                        //atm
                        marker = mMap.addMarker(new MarkerOptions().position(st_zoo).title("Standard Bank ATM\nZoo Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(st_zoo, 11));
                    } else if (position == 18) {
                        mMap.clear();
                        spinner2.setSelection(adapter2.getCount());
                        marker = mMap.addMarker(new MarkerOptions().position(tr_bir).title("Trust Bank Limited\nBir Uttam AK Khandakar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_bir, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(tr_gul).title("Trust Bank Limited\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_gul, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(tr_kemal).title("Trust Bank Limited\nKemal Ataturk Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_kemal, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(tr_cant).title("Trust Bank Limited\nCantonment").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_cant, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(tr_north1).title("Trust Bank Limited\nNorth Kafrul").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_north1, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(tr_shaheed).title("Trust Bank Limited\nShaheed Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_shaheed, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(tr_trade).title("Trust Bank Limited\nTrade Center").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_trade, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(tr_pragati).title("Trust Bank Limited\nPragati Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_pragati, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(tr_kakrail).title("Trust Bank Limited\nKakrail").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_kakrail, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(tr_pilkhana).title("Trust Bank Limited\nPilkhana").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_pilkhana, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(tr_sena).title("Trust Bank Limited\nSena Kalyan Bahban").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_sena, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(tr_dil).title("Trust Bank Limited\nDilkhusha").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_dil, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(tr_moha).title("Trust Bank Limited\nMohammadpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_moha, 11));
                        //atm
                        marker = mMap.addMarker(new MarkerOptions().position(tr_canto).title("Trust Bank ATM\nCantonment").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_canto, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(tr_shahed).title("Trust Bank ATM\nShaheed Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_shahed, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(tr_saudi).title("Trust Bank ATM\nSaudi Colony").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_saudi, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(tr_falcon).title("Trust Bank ATM\nFalcon Hall Adjacent").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_falcon, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(tr_rajni).title("Trust Bank ATM\nRajnigandha").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_rajni, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(tr_south).title("Trust Bank ATM\nSouth Gate Naval HQ").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_south, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(tr_navy).title("Trust Bank ATM\nNavy Head Quarter").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_navy, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(tr_army).title("Trust Bank ATM\nArmy Head Quarter").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_army, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(tr_cmh).title("Trust Bank ATM\nCMH Hospital").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_cmh, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(tr_prese).title("Trust Bank ATM\nGono Bhaban").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_prese, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(tr_bhan).title("Trust Bank ATM\nBhasantek").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_bhan, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(tr_apollo).title("Trust Bank ATM\nApollo Hospital Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_apollo, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(tr_dhan).title("Trust Bank ATM\nDhanmondi").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_dhan, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(tr_moham).title("Trust Bank ATM\nMohammadpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_moham, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(tr_kak).title("Trust Bank ATM\nKakrail").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_kak, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(tr_sat).title("Trust Bank ATM\nSatmosjid Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_sat, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(tr_mir).title("Trust Bank ATM\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_mir, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(tr_north).title("Trust Bank ATM\nPilkhana").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_north, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(tr_moti).title("Trust Bank ATM\nMotijheel").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_moti, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(tr_polashi).title("Trust Bank ATM\nAzimpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_polashi, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(tr_new).title("Trust Bank ATM\nNew Market").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_new, 11));
                    } else if (position == 19) {
                        mMap.clear();
                        spinner2.setSelection(adapter2.getCount());
                        marker = mMap.addMarker(new MarkerOptions().position(ut_bir).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_bir, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ut_kazi).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_kazi, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ut_shahid).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_shahid, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ut_green).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_green, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ut_begum).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_begum, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ut_outer).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_outer, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ut_selina).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_selina, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ut_pantha).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_pantha, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ut_ring).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_ring, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ut_lake).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_lake, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ut_bir_utta).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_bir_utta, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ut_hatkola).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_hatkola, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ut_tipu).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_tipu, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ut_lal).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_lal, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ut_nawabpur).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_nawabpur, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ut_begum_bazar).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_begum_bazar, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ut_vip).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_vip, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ut_bangla).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_bangla, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ut_new).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_new, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ut_kamla).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_kamla, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ut_johnson).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_johnson, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ut_azim).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_azim, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ut_mir).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_mir, 11));
                        //atm
                        marker = mMap.addMarker(new MarkerOptions().position(ut_bir_uttam).title("Uttara Bank ATM\nBir Uttam Rafiqul Islam Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_bir_uttam, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ut_shanti).title("Uttara Bank ATM\nShanti Nagar").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_shanti, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(ut_dar).title("Uttara Bank ATM\nDar-Us-Salam Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_dar, 11));
                    } else if (position == 20) {
                        mMap.clear();
                        spinner2.setSelection(adapter2.getCount());
                        marker = mMap.addMarker(new MarkerOptions().position(i_gul).title("Islami Bank Bangladesh Limited\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_gul, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(i_bir).title("Islami Bank Bangladesh Limited\nBir Uttam Rafiqul Islam Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_bir, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(i_bir_uttam).title("Islami Bank Bangladesh Limited\nBir Uttam Mir Shawkat Sarak").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_bir_uttam, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(i_new).title("Islami Bank Bangladesh Limited\nNew Circular Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_new, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(i_new_esk).title("Islami Bank Bangladesh Limited\nNew Eskaton Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_new_esk, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(i_pragati).title("Islami Bank Bangladesh Limited\nPragati Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_pragati, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(i_kachu).title("Islami Bank Bangladesh Limited\nKachukhet").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_kachu, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(i_begum).title("Islami Bank Bangladesh Limited\nBegum Rokeya Sarani Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_begum, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(i_idb).title("Islami Bank Bangladesh Limited\nIDB Bhaban").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_idb, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(i_kushal).title("Islami Bank Bangladesh Limited\nKushal Center").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_kushal, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(i_mazar).title("Islami Bank Bangladesh Limited\nMazar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_mazar, 11));
                        //atm
                        marker = mMap.addMarker(new MarkerOptions().position(i_gulshan).title("Islami Bank Bangladesh ATM\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_gulshan, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(i_bir_uttam_ave).title("Islami Bank Bangladesh ATM\nBir Uttam Rafiqul Islam Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_bir_uttam_ave, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(i_eskar).title("Islami Bank Bangladesh ATM\nNew Eskaton Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_eskar, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(i_kazi).title("Islami Bank Bangladesh ATM\nKazi Nazrul Islam Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_kazi, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(i_bashud).title("Islami Bank Bangladesh ATM\nBashundhara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_bashud, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(i_manikdi).title("Islami Bank Bangladesh ATM\nManikdi Bazar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_manikdi, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(i_mir).title("Islami Bank Bangladesh ATM\nMirpur Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_mir, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(i_shahedd).title("Islami Bank Bangladesh ATM\nShaheed Tajuddin Ahmed Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_shahedd, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(i_mazar_road).title("Islami Bank Bangladesh ATM\nMazar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_mazar_road, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(i_dhk1).title("Islami Bank Bangladesh ATM\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_dhk1, 11));
                    } else if (position == 21) {
                        mMap.clear();
                        spinner2.setSelection(adapter2.getCount());
                        marker = mMap.addMarker(new MarkerOptions().position(j_dhk1).title("Jamuna Bank Limited\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_dhk1, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(j_midas).title("Jamuna Bank Limited\nMidas Center").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_midas, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(j_moti).title("Jamuna Bank Limited\nMotijheel").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_moti, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(j_dhk2).title("Jamuna Bank Limited\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_dhk2, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(j_topkhana).title("Jamuna Bank Limited\nTopkhana Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_topkhana, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(j_chameli).title("Jamuna Bank Limited\nChameli").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_chameli, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(j_khales).title("Jamuna Bank Limited\nKhales Mansion").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_khales, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(j_bangshal).title("Jamuna Bank Limited\nBangshal").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_bangshal, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(j_dar).title("Jamuna Bank Limited\nDar-Us-Salam Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_dar, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(j_begum).title("Jamuna Bank Limited\nBegum Rokeya Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_begum, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(j_ismail).title("Jamuna Bank Limited\nIsmail Mansion").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_ismail, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(j_hazi).title("Jamuna Bank Limited\nHazi Ahmed Plaza").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_hazi, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(j_sector).title("Jamuna Bank Limited\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_sector, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(j_airport).title("Jamuna Bank Limited\nAirport").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_airport, 11));
                        //atm
                        marker = mMap.addMarker(new MarkerOptions().position(j_sonar).title("Jamuna Bank ATM\nSonargaon").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_sonar, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(j_ground).title("Jamuna Bank ATM\nAli Bhaban").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_ground, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(j_dil).title("Jamuna Bank ATM\nDilkhusha Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_dil, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(j_dit).title("Jamuna Bank ATM\nDIT Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_dit, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(j_bir_ut).title("Jamuna Bank ATM\nBir Uttam Samsul Alam Sarak").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_bir_ut, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(j_ground_floor).title("Jamuna Bank ATM\nShahjahanpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_ground_floor, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(j_cir).title("Jamuna Bank ATM\nCirculer Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_cir, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(j_rankin).title("Jamuna Bank ATM\nRankin Street").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_rankin, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(j_dhk3).title("Jamuna Bank ATM\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_dhk3, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(j_mirp).title("Jamuna Bank ATM\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_mirp, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(j_dar1).title("Jamuna Bank ATM\nDar-Us-Salam Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_dar1, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(j_milk).title("Jamuna Bank ATM\nMilk Vita Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_milk, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(j_s_kafrul).title("Jamuna Bank ATM\nKafrul").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_s_kafrul, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(j_mohak).title("Jamuna Bank ATM\nMohakhali").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_mohak, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(j_gul1).title("Jamuna Bank ATM\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_gul1, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(j_gausul).title("Jamuna Bank ATM\nGausul Azam Ave ").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_gausul, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(j_dhk_mym).title("Jamuna Bank ATM\nDhaka Mymensingh Highway").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_dhk_mym, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(j_sat).title("Jamuna Bank ATM\nSatmosjid Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_sat, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(j_atish).title("Jamuna Bank ATM\nAtish Deepenkar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_atish, 11));
                    } else if (position == 22) {
                        mMap.clear();
                        spinner2.setSelection(adapter2.getCount());
                        marker = mMap.addMarker(new MarkerOptions().position(na_samad).title("National Credit and Commerce Bank\nSamad Super Market").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_samad, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(na_shaheed).title("National Credit and Commerce Bank\nMogbazar").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_shaheed, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(na_shahid).title("National Credit and Commerce Bank\nTaslim Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_shahid, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(na_classic).title("National Credit and Commerce Bank\nClassic Center Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_classic, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(na_uttara).title("National Credit and Commerce Bank\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_uttara, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(na_spectra).title("National Credit and Commerce Bank\nSpectra Maqsood Tower").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_spectra, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(na_quality).title("National Credit and Commerce Bank\nQuality Center").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_quality, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(na_nazrul).title("National Credit and Commerce Bank\nShahid Syed Nazrul Islam Sharani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_nazrul, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(na_dhk1).title("National Credit and Commerce Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_dhk1, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(na_toyenbee).title("National Credit and Commerce Bank\nToyenbee Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_toyenbee, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(na_fahad).title("National Credit and Commerce Bank\nFahad Complex").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_fahad, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(na_motij).title("National Credit and Commerce Bank\nMotijheel").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_motij, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(na_nawab).title("National Credit and Commerce Bank\nNawabpur Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_nawab, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(na_babu).title("National Credit and Commerce Bank\nBabu Bazar").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_babu, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(na_dhk_mawa).title("National Credit and Commerce Bank\nDhaka Mymensingh Highway").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_dhk_mawa, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(na_bashu).title("National Credit and Commerce Bank\nBashundhara").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_bashu, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(na_kc).title("National Credit and Commerce Bank\nKC Plaza").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_kc, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(na_bangshal).title("National Credit and Commerce Bank\nBangshal").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_bangshal, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(na_pantha).title("National Credit and Commerce Bank\nPanthapath").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_pantha, 11));
                        //atm
                        marker = mMap.addMarker(new MarkerOptions().position(na_gulsh).title("National Credit and Commerce Bank ATM\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_gulsh, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(na_bbtoa).title("National Credit and Commerce Bank ATM\nBBTOA Building").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_bbtoa, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(na_mirpu).title("National Credit and Commerce Bank ATM\nBBTOA BUilding").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_mirpu, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(na_ram).title("National Credit and Commerce Bank ATM\nRam Krishna Mission").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_ram, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(na_dhk2).title("National Credit and Commerce Bank ATM\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_dhk2, 11));
                        marker = mMap.addMarker(new MarkerOptions().position(na_school).title("National Credit and Commerce Bank ATM\nSchool Super Market").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_school, 11));
                    } else if (position == 23) {
                        all_bank();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position == 0) {
                        //if (marker != null)
                        if (spinner.getSelectedItemPosition() == 0) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(b_gul).title("Brac Bank\nGulshan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_gul, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_ngul).title("Brac Bank\nNorth Gulshan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_ngul, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_banshundhara).title("Brac Bank\nBashundhara Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_banshundhara, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_asad).title("Brac Bank\nAsad Gate Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_asad, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_banani).title("Brac Bank\nBanani Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_banani, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_ashkona).title("Brac Bank\nAshkona Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_ashkona, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_samasjid).title("Brac Bank\nSatmosjod Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_samasjid, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_elephant).title("Brac Bank\nElephant Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_elephant, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_eskaton).title("Brac Bank\nEskaton").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_eskaton, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_mirpur).title("Brac Bank\nMirpur Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_mirpur, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_begum).title("Brac Bank\nBegum Rokeya Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_begum, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_mogbazar).title("Brac Bank\nMogbazar Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_mogbazar, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_motijheel).title("Brac Bank\nMotijheel Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_motijheel, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_notun).title("Brac Bank\nNatun Bazar Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_notun, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_banasree).title("Brac Bank\nBanasree").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_banasree, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_shymoli).title("Brac Bank\nShymoli Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_shymoli, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_dakk).title("Brac Bank\nDakkhin Khan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_dakk, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_shantinagar).title("Brac Bank\nShantinagar Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_shantinagar, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_uttra).title("Brac Bank\nUttara Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_uttra, 11));

                        } else if (spinner.getSelectedItemPosition() == 1) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(c_dil).title("City Bank\nDilkhusha Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_dil, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_banani).title("City Bank\nBanani Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_banani, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_dhan).title("City Bank\nDhanmondi 27 Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_dhan, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_kar).title("City Bank\nKarwanbazar Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_kar, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_mirpu).title("City Bank\nMirpur Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_mirpu, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_mog).title("City Bank\nMogbazar Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_mog, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_moti).title("City Bank\nMotijheel Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_moti, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_kakra).title("City Bank\nKakrail Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_kakra, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_dhan_2).title("City Bank\nDhanmondi 2 Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_dhan_2, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_pall).title("City Bank\nPallabi Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_pall, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_bad).title("City Bank\nBadda Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_bad, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_shy).title("City Bank\nShymoli Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_shy, 11));

                        } else if (spinner.getSelectedItemPosition() == 2) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(u_ban).title("United Commercial Bank\nBanani Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_ban, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(u_mad).title("United Commercial Bank\nMadani Avenue Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_mad, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(u_gul).title("United Commercial Bank\nGulshan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_gul, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(u_bas).title("United Commercial Bank\nBashundhara Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_bas, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(u_dhan).title("United Commercial Bank\nDhanmondi 27 Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_dhan, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(u_kat).title("United Commercial Bank\nKatabon Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_kat, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(u_gulshan).title("United Commercial Bank\nGulshan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_gulshan, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(u_kar).title("United Commercial Bank\nKarwanbazar Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_kar, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(u_esk).title("United Commercial Bank\nEskaton Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_esk, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(u_aftab).title("United Commercial Bank\nAftabnagar Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_aftab, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(u_sidd).title("United Commercial Bank\nSiddeswari Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_sidd, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(u_sha).title("United Commercial Bank\nShahid syed Nazrul Islam Avenue Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_sha, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(u_moti).title("United Commercial Bank\nMotijheel Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_moti, 11));

                        } else if (spinner.getSelectedItemPosition() == 3) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(d_sonar).title("Dutch Bangla Bank\nSonargaon Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_sonar, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(d_dit).title("Dutch Bangla Bank\nDIT Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_dit, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(d_kazi).title("Dutch Bangla Bank\nKazi nazrul Islam Avenue Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_kazi, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(d_shanti).title("Dutch Bangla Bank\nShanti Nagar Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_shanti, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(d_ash).title("Dutch Bangla Bank\nAshkona Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_ash, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(d_khil).title("Dutch Bangla Bank\nKhilkhet Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_khil, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(d_ring).title("Dutch Bangla Bank\nRing Road Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_ring, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(d_ele).title("Dutch Bangla Bank\nElephant Road Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_ele, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(d_dil).title("Dutch Bangla Bank\nDilkhusha Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_dil, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(d_sat).title("Dutch Bangla Bank\nSatmosjid Road Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_sat, 11));

                        } else if (spinner.getSelectedItemPosition() == 4) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(h_dha).title("HSBC Bank\nDhanmondi Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(h_dha, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(h_gul).title("HSBC Bank\nGulshan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(h_gul, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(h_mir).title("HSBC Bank\nMirpur Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(h_mir, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(h_sonar).title("HSBC Bank\nSonargoan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(h_sonar, 11));

                        } else if (spinner.getSelectedItemPosition() == 5) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(s_gul).title("Standard Chartered Bank\nGulshan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_gul, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(s_satm).title("Standard Chartered Bank\nSatmosjid Road Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_satm, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(s_dhan).title("Standard Chartered Bank\nDhanmondi Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_dhan, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(s_beg).title("Standard Chartered Bank\nBegum Rokeya Avenue Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_beg, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(s_banasree).title("Standard Chartered Bank\nBanasree Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_banasree, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(s_chaw).title("Standard Chartered Bank\nChawbazar Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_chaw, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(s_khil).title("Standard Chartered Bank\nKhilgaon Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_khil, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(s_utt).title("Standard Chartered Bank\nUttara Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_utt, 11));

                        } else if (spinner.getSelectedItemPosition() == 6) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(p_ban).title("Prime Bank\nBanani Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_ban, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_gul).title("Prime Bank\nGulshan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_gul, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_bas).title("Prime Bank\nBashundhara Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_bas, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_kach).title("Prime Bank\nKachukhet Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_kach, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_moh).title("Prime Bank\nMohakhali Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_moh, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_tej).title("Prime Bank\nTejgaon Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_tej, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_tejturi).title("Prime Bank\nTejturi Bazar Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_tejturi, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_mid).title("Prime Bank\nMiddle Badda Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_mid, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_dhanmond).title("Prime Bank\nDhanmondi Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_dhanmond, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_asad).title("Prime Bank\nAsad Avenue Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_asad, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_sat).title("Prime Bank\nSatmosjid Road Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_sat, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_new).title("Prime Bank\nNew Eskaton Road Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_new, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_ele).title("Prime Bank\nElephant Road Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_ele, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_beg).title("Prime Bank\nBegum Rokeya Sarani Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_beg, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_banasree).title("Prime Bank\nBanasree Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_banasree, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_ring).title("Prime Bank\nRing Road Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_ring, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_mali).title("Prime Bank\nMalibag Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_mali, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_seg).title("Prime Bank\nSegun Bagicha Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_seg, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_mir).title("Prime Bank\nMirpur Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_mir, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_moti).title("Prime Bank\nMotijheel Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_moti, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_south).title("Prime Bank\nSouth Bashabo Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_south, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_dil).title("Prime Bank\nDilkhusha Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_dil, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_dit).title("Prime Bank\nDIT Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_dit, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_lal).title("Prime Bank\nLalbag Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_lal, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_utt).title("Prime Bank\nUttara Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_utt, 11));

                        } else if (spinner.getSelectedItemPosition() == 7) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(e_mir).title("Eastern Bank\nMirpur Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_mir, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_mirpu).title("Eastern Bank\nMirpur Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_mirpu, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_ban).title("Eastern Bank\nBanani Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_ban, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_utt).title("Eastern Bank\nUttara Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_utt, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_ring).title("Eastern Bank\nRing Road Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_ring, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_dak).title("Eastern Bank\nDakshin Khan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_dak, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_uttara).title("Eastern Bank\nUttara Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_uttara, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_uttara_gr).title("Eastern Bank\nUttara Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_uttara_gr, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_kal).title("Eastern Bank\nKalabagan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_kal, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_bash).title("Eastern Bank\nBashundhara Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_bash, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_gul2).title("Eastern Bank\nGulshan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_gul2, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_gulave).title("Eastern Bank\nGulshan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_gulave, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_sonargoan).title("Eastern Bank\nSonargaon Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_sonargoan, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_gula).title("Eastern Bank\nGulshan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_gula, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_mog).title("Eastern Bank\nMogbazar Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_mog, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_shan).title("Eastern Bank\nShantinagar Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_shan, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_kak).title("Eastern Bank\nKakrail Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_kak, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_purana).title("Eastern Bank\nPurana Paltan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_purana, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_pil).title("Eastern Bank\nPilkhana Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_pil, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_shant).title("Eastern Bank\nShanti Nagar Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_shant, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_north).title("Eastern Bank\nNorth South Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_north, 11));
                        } else if (spinner.getSelectedItemPosition() == 8) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(ab_dhan).title("AB Bank Limited\nDhanmondi").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_dhan, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ab_gul).title("AB Bank Limited\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_gul, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ab_vip).title("AB Bank Limited\nVIP Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_vip, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ab_kazi).title("AB Bank Limited\nKazi Nazrul Islam Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_kazi, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ab_mali).title("AB Bank Limited\nMalibagh").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_mali, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ab_mir).title("AB Bank Limited\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_mir, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ab_dit).title("AB Bank Limited\nDIT Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_dit, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ab_moha).title("AB Bank Limited\nMohakhali").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_moha, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ab_bari).title("AB Bank Limited\nBaridhara").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_bari, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ab_she).title("AB Bank Limited\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_she, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ab_shymoli).title("AB Bank Limited\nShymoli").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_shymoli, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ab_uttara).title("AB Bank Limited\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_uttara, 11));
                        } else if (spinner.getSelectedItemPosition() == 9) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(bn_bashu).title("Bank Asia\nBashundhara").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_bashu, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(bn_sonar).title("Bank Asia\nSonargoan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_sonar, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(bn_dak).title("Bank Asia\nDakshinkhan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_dak, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(bn_meher).title("Bank Asia\nMeher Plaza").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_meher, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(bn_ele).title("Bank Asia\nElephant Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_ele, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(bn_eska).title("Bank Asia\nEskaton").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_eska, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(bn_gul).title("Bank Asia\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_gul, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(bn_ban).title("Bank Asia\nBanani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_ban, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(bn_dil).title("Bank Asia\nDilkhusha").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_dil, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(bn_pallabi).title("Bank Asia\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_pallabi, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(bn_moha).title("Bank Asia\nMohakhali").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_moha, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(bn_pal).title("Bank Asia\nPurana Paltan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_pal, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(bn_moti).title("Bank Asia\nMotijheel").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_moti, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(bn_progati).title("Bank Asia\nProgoti Soroni").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_progati, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(bn_ring).title("Bank Asia\nRing Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_ring, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(bn_rup_mir).title("Bank Asia\nRupnagar Mirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_rup_mir, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(bn_bijoy).title("Bank Asia\nBijoy Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_bijoy, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(bn_cir).title("Bank Asia\nCircular Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_cir, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(bn_shymoli).title("Bank Asia\nShymoli").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_shymoli, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(bn_taj).title("Bank Asia\nTajgaon").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_taj, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(bn_utt).title("Bank Asia\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_utt, 11));
                        } else if (spinner.getSelectedItemPosition() == 10) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(ex_pragati).title("Exim Bank\nPragati Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_pragati, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ex_gul).title("Exim Bank\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_gul, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ex_pantha).title("Exim Bank\nPanthapath").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_pantha, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ex_bgmea).title("Exim Bank\nPnthapath").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_bgmea, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ex_gul2).title("Exim Bank\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_gul2, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ex_rokeya).title("Exim Bank\nBegum Rokeya Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_rokeya, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ex_dit).title("Exim Bank\nDIT Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_dit, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ex_pra).title("Exim Bank\nPragati Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_pra, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ex_ele).title("Exim Bank\nElephant Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_ele, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ex_moti).title("Exim Bank\nMotijheel").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_moti, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ex_sat).title("Exim Bank\nSatmosjid Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_sat, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ex_rokeya_sarani).title("Exim Bank\nBegum Rokeya Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_rokeya_sarani, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ex_ring).title("Exim Bank\nRing Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_ring, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ex_nawabpur).title("Exim Bank\nNawabpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_nawabpur, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ex_garib).title("Exim Bank\nGarib-e-Newaz").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_garib, 11));
                        } else if (spinner.getSelectedItemPosition() == 11) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(m_pragati).title("Mercantile Bank\nPragati Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_pragati, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(m_kachu).title("Mercantile Bank\nKachukhet").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_kachu, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(m_rokeya).title("Mercantile Bank\nBegum Rokeya Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_rokeya, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(m_gul).title("Mercantile Bank\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_gul, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(m_moha).title("Mercantile Bank\nMohakhali").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_moha, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(m_moha_bir).title("Mercantile Bank\nMohakhali").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_moha_bir, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(m_ring).title("Mercantile Bank\nRing Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_ring, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(m_dit).title("Mercantile Bank\nDIT Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_dit, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(m_dhaka).title("Mercantile Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_dhaka, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(m_pantha).title("Mercantile Bank\nPanthapath").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_pantha, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(m_kazi).title("Mercantile Bank\nKazi Nazrul Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_kazi, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(m_sat).title("Mercantile Bank\nSatmosjid Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_sat, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(m_cir).title("Mercantile Bank\nCirculer Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_cir, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(m_jaha).title("Mercantile Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_jaha, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(m_moulana).title("Mercantile Bank\nMoulana Vasani Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_moulana, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(m_sharani).title("Mercantile Bank\nShahid Syed Nazrul Islam Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_sharani, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(m_malek).title("Mercantile Bank\nMalek Mansion").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_malek, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(m_pass).title("Mercantile Bank\nPresident Pass").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_pass, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(m_moulo).title("Mercantile Bank\nMoulovibazar Dhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_moulo, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(m_lal).title("Mercantile Bank\nLal Mohan Shaha Street").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_lal, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(m_indira).title("Mercantile Bank\nIndira Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_indira, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(m_asad).title("Mercantile Bank\nAsad Gate").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_asad, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(m_manikdi).title("Mercantile Bank\nManikdi Bazar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_manikdi, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(m_ibra).title("Mercantile Bank\nIbrahimpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_ibra, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(m_shahid).title("Mercantile Bank\nShahid Latif Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_shahid, 11));
                        } else if (spinner.getSelectedItemPosition() == 12) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(mu_pragati).title("Mutual Trust Bank\nPragati Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_pragati, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_dhk).title("Mutual Trust Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_dhk, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_dhk1).title("Mutual Trust Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_dhk1, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_gul).title("Mutual Trust Bank\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_gul, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_mir).title("Mutual Trust Bank\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_mir, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_dhk2).title("Mutual Trust Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_dhk2, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_bir).title("Mutual Trust Bank\nBir Uttam Rafiqul Islam Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_bir, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_gulshan).title("Mutual Trust Bank\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_gulshan, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_dhk3).title("Mutual Trust Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_dhk3, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_bir_uttam).title("Mutual Trust Bank\nBir Uttam Mir Shawkat Sarak").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_bir_uttam, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_service).title("Mutual Trust Bank\nService Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_service, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_dhk4).title("Mutual Trust Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_dhk4, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_shah).title("Mutual Trust Bank\nShah Mokhdum Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_shah, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_moha).title("Mutual Trust Bank\nMohammadpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_moha, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_hatir).title("Mutual Trust Bank\nHatirjheel").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_hatir, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_chandra).title("Mutual Trust Bank\nChandrashila Suvastu Tower").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_chandra, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_dhk5).title("Mutual Trust Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_dhk5, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_ele).title("Mutual Trust Bank\nElephant Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_ele, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_uttara).title("Mutual Trust Bank\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_uttara, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_samsul).title("Mutual Trust Bank\nBir Uttam Samsul Alam Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_samsul, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_central).title("Mutual Trust Bank\nCentral Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_central, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_moti).title("Mutual Trust Bank\nMotijheel").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_moti, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_ramna).title("Mutual Trust Bank\nRamna").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_ramna, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_dil).title("Mutual Trust Bank\nDilkhusha").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_dil, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_motijheel).title("Mutual Trust Bank\nMotijheel").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_motijheel, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_kazi).title("Mutual Trust Bank\nKazi Nazrul Islam Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_kazi, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_chowk).title("Mutual Trust Bank\nLalbag").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_chowk, 11));
                        } else if (spinner.getSelectedItemPosition() == 13) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(pr_gul).title("The Premier Bank\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pr_gul, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(pr_kemal).title("The Premier Bank\nKemal Ataturk Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pr_kemal, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(pr_dhk1).title("The Premier Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pr_dhk1, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(pr_bir).title("The Premier Bank\nBir Uttam AK Khandakar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pr_bir, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(pr_mir).title("The Premier Bank\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pr_mir, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(pr_begum).title("The Premier Bank\nBegum Rokeya Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pr_begum, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(pr_agrani).title("The Premier Bank\nAgrani Midtown Complex").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pr_agrani, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(pr_ring).title("The Premier Bank\nRing Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pr_ring, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(pr_new).title("The Premier Bank\nNew Market").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pr_new, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(pr_vip).title("The Premier Bank\nVIP Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pr_vip, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(pr_air).title("The Premier Bank\nAirport").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pr_air, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(pr_dhk2).title("The Premier Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pr_dhk2, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(pr_momen).title("The Premier Bank\nMomenbug").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pr_momen, 11));
                        } else if (spinner.getSelectedItemPosition() == 14) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(o_kak).title("One Bank\nKakrail").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(o_kak, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(o_alam).title("One Bank\nAlam Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(o_alam, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(o_lal).title("One Bank\nLalbagh").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(o_lal, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(o_al).title("One Bank\nAL Kauser Palace").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(o_al, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(o_selina).title("One Bank\nShahid Shagbadik Selina Parvin Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(o_selina, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(o_dhk1).title("One Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(o_dhk1, 11));
                        } else if (spinner.getSelectedItemPosition() == 15) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(se_simpl).title("SouthEast Bank\nSimpletree").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_simpl, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(se_begum).title("SouthEast Bank\nBegum Rokeya Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_begum, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(se_dhk1).title("SouthEast Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_dhk1, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(se_bir).title("SouthEast Bank\nBir Uttam AK Khandakar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_bir, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(se_gul).title("SouthEast Bank\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_gul, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(se_islampur).title("SouthEast Bank\nIslampur").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_islampur, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(se_eska).title("SouthEast Bank\nEskaton").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_eska, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(se_mir).title("SouthEast Bank\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_mir, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(se_dhk2).title("SouthEast Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_dhk2, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(se_ele).title("SouthEast Bank\nElephant Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_ele, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(se_rafi).title("SouthEast Bank\nBir Uttam Rafiqul Islam Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_rafi, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(se_bana).title("SouthEast Bank\nBanasree").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_bana, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(se_mog).title("SouthEast Bank\nMogbazar").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_mog, 11));
                        } else if (spinner.getSelectedItemPosition() == 16) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(hk_dhk1).title("The HongKong and Shanghai Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hk_dhk1, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(hk_dhk2).title("The HongKong and Shanghai Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hk_dhk2, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(hk_begum).title("The HongKong and Shanghai Bank\nBegum Rokeya Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hk_begum, 11));
                        } else if (spinner.getSelectedItemPosition() == 17) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(st_gul).title("Standard Bank\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(st_gul, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(st_bir).title("Standard Bank\nBir Uttam AK Khandakar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(st_bir, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(st_pragati).title("Standard Bank\nPragati Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(st_pragati, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(st_moha).title("Standard Bank\nMohammadpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(st_moha, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(st_dhan).title("Standard Bank\nDhanmondi").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(st_dhan, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(st_lions).title("Standard Bank\nNorthern Lions").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(st_lions, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(st_dhk1).title("Standard Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(st_dhk1, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(st_cresent).title("Standard Bank\nTopkhana").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(st_cresent, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(st_moti).title("Standard Bank\nMotijheel").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(st_moti, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(st_air).title("Standard Bank\nAirport").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(st_air, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(st_shahi).title("Standard Bank\nShahi Bhaban").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(st_shahi, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(st_momen).title("Standard Bank\nMomenbug Chowrasta").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(st_momen, 11));
                        } else if (spinner.getSelectedItemPosition() == 18) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(tr_bir).title("Trust Bank Limited\nBir Uttam AK Khandakar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_bir, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(tr_gul).title("Trust Bank Limited\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_gul, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(tr_kemal).title("Trust Bank Limited\nKemal Ataturk Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_kemal, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(tr_cant).title("Trust Bank Limited\nCantonment").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_cant, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(tr_north1).title("Trust Bank Limited\nNorth Kafrul").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_north1, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(tr_shaheed).title("Trust Bank Limited\nShaheed Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_shaheed, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(tr_trade).title("Trust Bank Limited\nTrade Center").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_trade, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(tr_pragati).title("Trust Bank Limited\nPragati Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_pragati, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(tr_kakrail).title("Trust Bank Limited\nKakrail").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_kakrail, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(tr_pilkhana).title("Trust Bank Limited\nPilkhana").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_pilkhana, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(tr_sena).title("Trust Bank Limited\nSena Kalyan Bahban").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_sena, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(tr_dil).title("Trust Bank Limited\nDilkhusha").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_dil, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(tr_moha).title("Trust Bank Limited\nMohammadpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_moha, 11));
                        } else if (spinner.getSelectedItemPosition() == 19) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(ut_bir).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_bir, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ut_kazi).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_kazi, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ut_shahid).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_shahid, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ut_green).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_green, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ut_begum).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_begum, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ut_outer).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_outer, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ut_selina).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_selina, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ut_pantha).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_pantha, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ut_ring).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_ring, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ut_lake).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_lake, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ut_bir_utta).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_bir_utta, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ut_hatkola).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_hatkola, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ut_tipu).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_tipu, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ut_lal).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_lal, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ut_nawabpur).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_nawabpur, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ut_begum_bazar).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_begum_bazar, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ut_vip).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_vip, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ut_bangla).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_bangla, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ut_new).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_new, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ut_kamla).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_kamla, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ut_johnson).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_johnson, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ut_azim).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_azim, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ut_mir).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_mir, 11));
                        } else if (spinner.getSelectedItemPosition() == 20) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(i_gul).title("Islami Bank Bangladesh Limited\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_gul, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(i_bir).title("Islami Bank Bangladesh Limited\nBir Uttam Rafiqul Islam Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_bir, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(i_bir_uttam).title("Islami Bank Bangladesh Limited\nBir Uttam Mir Shawkat Sarak").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_bir_uttam, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(i_new).title("Islami Bank Bangladesh Limited\nNew Circular Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_new, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(i_new_esk).title("Islami Bank Bangladesh Limited\nNew Eskaton Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_new_esk, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(i_pragati).title("Islami Bank Bangladesh Limited\nPragati Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_pragati, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(i_kachu).title("Islami Bank Bangladesh Limited\nKachukhet").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_kachu, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(i_begum).title("Islami Bank Bangladesh Limited\nBegum Rokeya Sarani Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_begum, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(i_idb).title("Islami Bank Bangladesh Limited\nIDB Bhaban").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_idb, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(i_kushal).title("Islami Bank Bangladesh Limited\nKushal Center").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_kushal, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(i_mazar).title("Islami Bank Bangladesh Limited\nMazar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_mazar, 11));
                        } else if (spinner.getSelectedItemPosition() == 21) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(j_dhk1).title("Jamuna Bank Limited\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_dhk1, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(j_midas).title("Jamuna Bank Limited\nMidas Center").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_midas, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(j_moti).title("Jamuna Bank Limited\nMotijheel").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_moti, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(j_dhk2).title("Jamuna Bank Limited\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_dhk2, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(j_topkhana).title("Jamuna Bank Limited\nTopkhana Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_topkhana, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(j_chameli).title("Jamuna Bank Limited\nChameli").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_chameli, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(j_khales).title("Jamuna Bank Limited\nKhales Mansion").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_khales, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(j_bangshal).title("Jamuna Bank Limited\nBangshal").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_bangshal, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(j_dar).title("Jamuna Bank Limited\nDar-Us-Salam Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_dar, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(j_begum).title("Jamuna Bank Limited\nBegum Rokeya Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_begum, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(j_ismail).title("Jamuna Bank Limited\nIsmail Mansion").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_ismail, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(j_hazi).title("Jamuna Bank Limited\nHazi Ahmed Plaza").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_hazi, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(j_sector).title("Jamuna Bank Limited\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_sector, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(j_airport).title("Jamuna Bank Limited\nAirport").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_airport, 11));
                        } else if (spinner.getSelectedItemPosition() == 22) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(na_samad).title("National Credit and Commerce Bank\nSamad Super Market").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_samad, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(na_shaheed).title("National Credit and Commerce Bank\nMogbazar").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_shaheed, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(na_shahid).title("National Credit and Commerce Bank\nTaslim Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_shahid, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(na_classic).title("National Credit and Commerce Bank\nClassic Center Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_classic, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(na_uttara).title("National Credit and Commerce Bank\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_uttara, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(na_spectra).title("National Credit and Commerce Bank\nSpectra Maqsood Tower").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_spectra, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(na_quality).title("National Credit and Commerce Bank\nQuality Center").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_quality, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(na_nazrul).title("National Credit and Commerce Bank\nShahid Syed Nazrul Islam Sharani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_nazrul, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(na_dhk1).title("National Credit and Commerce Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_dhk1, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(na_toyenbee).title("National Credit and Commerce Bank\nToyenbee Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_toyenbee, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(na_fahad).title("National Credit and Commerce Bank\nFahad Complex").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_fahad, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(na_motij).title("National Credit and Commerce Bank\nMotijheel").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_motij, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(na_nawab).title("National Credit and Commerce Bank\nNawabpur Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_nawab, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(na_babu).title("National Credit and Commerce Bank\nBabu Bazar").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_babu, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(na_dhk_mawa).title("National Credit and Commerce Bank\nDhaka Mymensingh Highway").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_dhk_mawa, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(na_bashu).title("National Credit and Commerce Bank\nBashundhara").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_bashu, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(na_kc).title("National Credit and Commerce Bank\nKC Plaza").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_kc, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(na_bangshal).title("National Credit and Commerce Bank\nBangshal").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_bangshal, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(na_pantha).title("National Credit and Commerce Bank\nPanthapath").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_pantha, 11));
                        }
                        //}
                    } else if (position == 1) {
                        //if (marker != null) {
                        if (spinner.getSelectedItemPosition() == 0) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(b_kamal).title("Brac Bank ATM\nKamal Ataturk Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_kamal, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_banani_1).title("Brac Bank ATM\nBanani").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_banani_1, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_agargoan).title("Brac Bank ATM\nAgargaon").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_agargoan, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_farm).title("Brac Bank ATM\nFarmgate").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_farm, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_azim).title("Brac Bank ATM\nAzimpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_azim, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_gulatm).title("Brac Bank ATM\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_gulatm, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_prag).title("Brac Bank ATM\nPragati Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_prag, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_kac).title("Brac Bank ATM\nKachukhet").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_kac, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_khilkhet).title("Brac Bank ATM\nKhilkhet").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_khilkhet, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_bir).title("Brac Bank ATM\nBir Uttam Rafiqul Islam Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_bir, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_air).title("Brac Bank ATM\nAirport Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_air, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_ibra).title("Brac Bank ATM\nIbrahimpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_ibra, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_sha).title("Brac Bank ATM\nShaheed Tajuddin Ahmed Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_sha, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_sonar).title("Brac Bank ATM\nSonargaon").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_sonar, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_new).title("Brac Bank ATM\nNew Eskaton").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_new, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_khil).title("Brac Bank ATM\nKhilgaon Taltola").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_khil, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_aftab).title("Brac Bank ATM\nAftab Nagar").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_aftab, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_kazi).title("Brac Bank ATM\nKazi Nazrul Islam Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_kazi, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_pant).title("Brac Bank ATM\nPanthapath").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_pant, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_begum_rokeya).title("Brac Bank ATM\nBegum Rokeya Avenue").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_begum_rokeya, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_moti).title("Brac Bank ATM\nMotijheel").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_moti, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_indi).title("Brac Bank ATM\nIndira Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_indi, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(b_rampura).title("Brac Bank ATM\nRampura DIT Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_rampura, 11));
                        } else if (spinner.getSelectedItemPosition() == 1) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(c_dilkhusa).title("City Bank ATM\nDilkhusha").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_dilkhusa, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_rif).title("City Bank ATM\nRiffle Squre").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_rif, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_pant).title("City Bank ATM\nPanthapath").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_pant, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_gre).title("City Bank ATM\nGreen Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_gre, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_wari).title("City Bank ATM\nWari").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_wari, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_ban).title("City Bank ATM\nBanani").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_ban, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_moh).title("City Bank ATM\nMohakhali").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_moh, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_baily).title("City Bank ATM\nBaily Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_baily, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_sat).title("City Bank ATM\nSatmosjid Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_sat, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_moni).title("City Bank ATM\nMonipuripara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_moni, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_pal).title("City Bank ATM\nPallabi").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_pal, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_mogb).title("City Bank ATM\nMogbazar").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_mogb, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_rok).title("City Bank ATM\nRokeya Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_rok, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_bana).title("City Bank ATM\nBanasree").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_bana, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_khil).title("City Bank ATM\nKhilgaon").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_khil, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_gul).title("City Bank ATM\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_gul, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_utt).title("City Bank ATM\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_utt, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_seg).title("City Bank ATM\nSegun Bagicha").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_seg, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_mir).title("City Bank ATM\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_mir, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_west).title("City Bank ATM\nRampura").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_west, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_dar).title("City Bank ATM\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_dar, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_bash).title("City Bank ATM\nBashundhara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_bash, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_fak).title("City Bank ATM\nFakirapool").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_fak, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_kam).title("City Bank ATM\nKamlapur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_kam, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_jan).title("City Bank ATM\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_jan, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_ele).title("City Bank ATM\nElephant Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_ele, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_shah).title("City Bank ATM\nShahjadpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_shah, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_dhanmondi).title("City Bank ATM\nDhanmondi").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_dhanmondi, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_uttara).title("City Bank ATM\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_uttara, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_mirpur).title("City Bank ATM\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_mirpur, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_elephant).title("City Bank ATM\nNew Elephant Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_elephant, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_mirpur_rup).title("City Bank ATM\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_mirpur_rup, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_kak).title("City Bank ATM\nKakrail").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_kak, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_dhaka).title("City Bank ATM\nDhaka Stoke Exchange").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_dhaka, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_rabi).title("City Bank ATM\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_rabi, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_prag).title("City Bank ATM\nPragati Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_prag, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_merul).title("City Bank ATM\nMerul Badda").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_merul, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(c_nik).title("City Bank ATM\nNikunja").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_nik, 11));
                        } else if (spinner.getSelectedItemPosition() == 2) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(u_bash).title("United Commercial ATM\nBashundhara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_bash, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(u_gus).title("United Commercial ATM\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_gus, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(u_ring).title("United Commercial ATM\nRing Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_ring, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(u_asad).title("United Commercial ATM\nAsad Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_asad, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(u_kal).title("United Commercial ATM\nKalabagan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_kal, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(u_dhanmo).title("United Commercial ATM\nDhanmondi").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_dhanmo, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(u_bir).title("United Commercial ATM\nBir Uttam Mir Shakwat Ali Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_bir, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(u_lal).title("United Commercial ATM\nLalbagh").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_lal, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(u_pant).title("United Commercial ATM\nPanthapath").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_pant, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(u_new).title("United Commercial ATM\nNew Elephant Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_new, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(u_motijheel).title("United Commercial ATM\nMotijheel").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_motijheel, 11));

                        } else if (spinner.getSelectedItemPosition() == 3) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(d_cant).title("Dutch Bangla Bank ATM\nCantonment").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_cant, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(d_utt).title("Dutch Bangla Bank ATM\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_utt, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(d_vas).title("Dutch Bangla Bank ATM\nVashantek").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_vas, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(d_sha).title("Dutch Bangla Bank ATM\nShahid latif Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_sha, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(d_shah).title("Dutch Bangla Bank ATM\nShah Kabir Mazar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_shah, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(d_air).title("Dutch Bangla Bank ATM\nAirport").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_air, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(d_ashkona).title("Dutch Bangla Bank ATM\nAshkona").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_ashkona, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(d_gau).title("Dutch Bangla Bank ATM\nGausul Azam Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_gau, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(d_sonargoan).title("Dutch Bangla Bank ATM\nSonargaon").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_sonargoan, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(d_taj).title("Dutch Bangla Bank ATM\nTejgaon").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_taj, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(d_ram).title("Dutch Bangla Bank ATM\nRampura").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_ram, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(d_banasree).title("Dutch Bangla Bank ATM\nBanasree").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_banasree, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(d_khilkhet).title("Dutch Bangla Bank ATM\nKhilgaon").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_khilkhet, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(d_new).title("Dutch Bangla Bank ATM\nNew Eskaton Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_new, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(d_karwan).title("Dutch Bangla Bank ATM\nKarwanbazar").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_karwan, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(d_shantinagar).title("Dutch Bangla Bank ATM\nShantinagar").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_shantinagar, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(d_baily).title("Dutch Bangla Bank ATM\nBaily Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_baily, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(d_shahbag).title("Dutch Bangla Bank ATM\nShahbag").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_shahbag, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(d_moti).title("Dutch Bangla Bank ATM\nMotijheel").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_moti, 11));

                        } else if (spinner.getSelectedItemPosition() == 4) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(h_ram).title("HSBC Bank ATM\nRampura").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(h_ram, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(h_gul2).title("HSBC Bank ATM\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(h_gul2, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(h_gul1).title("HSBC Bank ATM\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(h_gul1, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(h_bash).title("HSBC Bank ATM\nBashundhara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(h_bash, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(h_chak).title("HSBC Bank ATM\nChawbazar").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(h_chak, 11));

                        } else if (spinner.getSelectedItemPosition() == 5) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(s_ring).title("Standard Chartered ATM\nRing Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_ring, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(s_begum).title("Standard Chartered ATM\nBegum Rokeya Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_begum, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(s_mog).title("Standard Chartered ATM\nMogbazar").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_mog, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(s_khilkhet).title("Standard Chartered ATM\nKhilkhet").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_khilkhet, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(s_bangla).title("Standard Chartered ATM\nBangla Academy Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_bangla, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(s_lal).title("Standard Chartered ATM\nLalbagh").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_lal, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(s_banani).title("Standard Chartered ATM\nBanani").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_banani, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(s_sat).title("Standard Chartered ATM\nSatmosjid Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_sat, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(s_dhanmon).title("Standard Chartered ATM\nDhanmondi").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_dhanmon, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(s_gulshan).title("Standard Chartered ATM\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_gulshan, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(s_gul1).title("Standard Chartered ATM\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_gul1, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(s_farm).title("Standard Chartered ATM\nFarmgate").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_farm, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(s_bashu).title("Standard Chartered ATM\nBashundhara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_bashu, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(s_agar).title("Standard Chartered ATM\nAgargaon").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_agar, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(s_dit).title("Standard Chartered ATM\nDIT Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_dit, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(s_gre).title("Standard Chartered ATM\nGreen Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_gre, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(s_new).title("Standard Chartered ATM\nNew Baily Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_new, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(s_segu).title("Standard Chartered ATM\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_segu, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(s_uttara).title("Standard Chartered ATM\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_uttara, 11));

                        } else if (spinner.getSelectedItemPosition() == 6) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(p_taj).title("Prime Bank ATM\nTajgaon").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_taj, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_bij).title("Prime Bank ATM\nBijoy Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_bij, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_pant).title("Prime Bank ATM\nPanthapath").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_pant, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_satmas).title("Prime Bank ATM\nSatmosjid Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_satmas, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_ram).title("Prime Bank ATM\nRampura").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_ram, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_elephant).title("Prime Bank ATM\nElephant Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_elephant, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_tajmahal).title("Prime Bank ATM\nTajmahal Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_tajmahal, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_shy).title("Prime Bank ATM\nShymoli").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_shy, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_dhanmondi).title("Prime Bank ATM\nDhanmondi").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_dhanmondi, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_begu).title("Prime Bank ATM\nBegum Rokeya Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_begu, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_ramna).title("Prime Bank ATM\nRamna").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_ramna, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_lalbag).title("Prime Bank ATM\nLalbagh").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_lalbag, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_shidd).title("Prime Bank ATM\nShiddeswari Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_shidd, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_tal).title("Prime Bank ATM\nTaltola").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_tal, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_begum).title("Prime Bank ATM\nBegum Rokeya Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_begum, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_rup).title("Prime Bank ATM\nRupnagar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_rup, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_dilkhusa).title("Prime Bank ATM\nDilkhusha").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_dilkhusa, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(p_uttara).title("Prime Bank ATM\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_uttara, 11));

                        } else if (spinner.getSelectedItemPosition() == 7) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(e_rupnagar).title("Eastern Bank ATM\nRupnagar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_rupnagar, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_uttara).title("Eastern Bank ATM\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_uttara, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_moh).title("Eastern Bank ATM\nMohammadpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_moh, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_azam).title("Eastern Bank ATM\nAzampur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_azam, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_shmoli).title("Eastern Bank ATM\nShymoli").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_shmoli, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_moha).title("Eastern Bank ATM\nMohammadi Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_moha, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_tajmohol).title("Eastern Bank ATM\nTajmohol Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_tajmohol, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_gulshan).title("Eastern Bank ATM\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_gulshan, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_new).title("Eastern Bank ATM\nNew Airport Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_new, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_uttara_garib).title("Eastern Bank ATM\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_uttara_garib, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_azad).title("Eastern Bank ATM\nAsad Gate").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_azad, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_bijoy).title("Eastern Bank ATM\nBijoy Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_bijoy, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_mohakhali).title("Eastern Bank ATM\nMohakhali").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_mohakhali, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_farmgate).title("Eastern Bank ATM\nFarmgate").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_farmgate, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_shi).title("Eastern Bank ATM\nShimanto Square").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_shi, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_mirpur).title("Eastern Bank ATM\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_mirpur, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_karwan).title("Eastern Bank ATM\nKarwanbazar").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_karwan, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_elep).title("Eastern Bank ATM\nElephant Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_elep, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_gulshan_nav).title("Eastern Bank ATM\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_gulshan_nav, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_moni).title("Eastern Bank ATM\nMonipuripara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_moni, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_pant).title("Eastern Bank ATM\nPanthapath").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_pant, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_new_b).title("Eastern Bank ATM\nNew Baily Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_new_b, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_shidd).title("Eastern Bank ATM\nShiddheswari Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_shidd, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_shanr).title("Eastern Bank ATM\nShantinagar").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_shanr, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(e_dilkh).title("Eastern Bank ATM\nDilkhusha").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_dilkh, 11));
                        } else if (spinner.getSelectedItemPosition() == 8) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(ab_dhanmondi).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_dhanmondi, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ab_dhanmondi_1).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_dhanmondi_1, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ab_dhanmondi_2).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_dhanmondi_2, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ab_dhanmondi_3).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_dhanmondi_3, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ab_dhanmondi_4).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_dhanmondi_4, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ab_dhanmondi_5).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_dhanmondi_5, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ab_ban).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_ban, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ab_baridhara).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_baridhara, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ab_gulsh).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_gulsh, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ab_south_gul).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_south_gul, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ab_ring).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_ring, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ab_gulshan).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_gulshan, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ab_kak).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_kak, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ab_kazi_nazrul).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_kazi_nazrul, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ab_malibagh_1).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_malibagh_1, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ab_bashu).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_bashu, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ab_manik).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_manik, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ab_mirp).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_mirp, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ab_mir_1).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_mir_1, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ab_mir_2).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_mir_2, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ab_mir_rup).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_mir_rup, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ab_mir_sec).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_mir_sec, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ab_utt).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_utt, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ab_dak).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_dak, 11));

                        } else if (spinner.getSelectedItemPosition() == 9) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(bn_bashundhara).title("Bank Asia ATM\nBashundhara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_bashundhara, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(bn_eskaton).title("Bank Asia ATM\nEskaton").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_eskaton, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(bn_sonargaon).title("Bank Asia ATM\nSonargaon").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_sonargaon, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(bn_dhanmond).title("Bank Asia ATM\nDhanmondi").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_dhanmond, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(bn_dilku).title("Bank Asia ATM\nDilkhusha").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_dilku, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(bn_garden).title("Bank Asia ATM\nEskaton").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_garden, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(bn_bays).title("Bank Asia ATM\nBays Galleria").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_bays, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(bn_sat).title("Bank Asia ATM\nSatmosjid Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_sat, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(bn_mirpur).title("Bank Asia ATM\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_mirpur, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(bn_moham).title("Bank Asia ATM\nMohammadpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_moham, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(bn_kamla).title("Bank Asia ATM\nKamlapur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_kamla, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(bn_paltan).title("Bank Asia ATM\nPaltan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_paltan, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(bn_ring_road).title("Bank Asia ATM\nRing Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_ring_road, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(bn_shym).title("Bank Asia ATM\nShymoli").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_shym, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(bn_nazrul_avenue).title("Bank Asia ATM\nKazi Nazrul Islam Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_nazrul_avenue, 11));

                        } else if (spinner.getSelectedItemPosition() == 10) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(ex_dhk).title("Exim Bank ATM\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_dhk, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ex_ring_road).title("Exim Bank ATM\nRing Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_ring_road, 11));

                        } else if (spinner.getSelectedItemPosition() == 11) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(m_mazar).title("Mercantile Bank ATM\nMazar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_mazar, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(m_dhk).title("Mercantile Bank ATM\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_dhk, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(m_haka).title("Mercantile Bank ATM\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_haka, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(m_shezan).title("Mercantile Bank ATM\nShezan Point").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_shezan, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(m_shahid_baki).title("Mercantile Bank ATM\nShahid Baki Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_shahid_baki, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(m_atish).title("Mercantile Bank ATM\nAtish Diponkar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_atish, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(m_purana_paltan).title("Mercantile Bank ATM\nPurana paltan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_purana_paltan, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(m_dit_1).title("Mercantile Bank ATM\nDIT Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_dit_1, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(m_north_kamla).title("Mercantile Bank ATM\nNorth Kamlapur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_north_kamla, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(m_pilkhana).title("Mercantile Bank ATM\nPilkhana").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_pilkhana, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(m_rankin).title("Mercantile Bank ATM\nRankin Street").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_rankin, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(m_vasani).title("Mercantile Bank ATM\nMoulana Vasani Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_vasani, 11));

                        } else if (spinner.getSelectedItemPosition() == 12) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(mu_dhk6).title("Mutual Trust Bank ATM\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_dhk6, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_dit).title("Mutual Trust Bank ATM\nDIT Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_dit, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_kemal).title("Mutual Trust Bank ATM\nKemal Ataturk Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_kemal, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_dhk7).title("Mutual Trust Bank ATM\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_dhk7, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_terminal).title("Mutual Trust Bank ATM\nTerminal").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_terminal, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_dhk8).title("Mutual Trust Bank ATM\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_dhk8, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_jashi).title("Mutual Trust Bank ATM\nJashiuddin Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_jashi, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_sonar).title("Mutual Trust Bank ATM\nSoanrgaon").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_sonar, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_dhk9).title("Mutual Trust Bank ATM\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_dhk9, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_abc).title("Mutual Trust Bank ATM\nABC Heritage").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_abc, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_pantha).title("Mutual Trust Bank ATM\nPanthapath").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_pantha, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_esk).title("Mutual Trust Bank ATM\nEskaton").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_esk, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_dit_road).title("Mutual Trust Bank ATM\nDIT Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_dit_road, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_kata).title("Mutual Trust Bank ATM\nKatabon").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_kata, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_toyen).title("Mutual Trust Bank ATM\nToyenbee Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_toyen, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mu_hatkola).title("Mutual Trust Bank ATM\nHotkola Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_hatkola, 11));

                        } else if (spinner.getSelectedItemPosition() == 13) {
                            //mMap.clear();

                        } else if (spinner.getSelectedItemPosition() == 14) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(o_dil).title("One Bank ATM\nDilkhusha").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(o_dil, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(o_kawran).title("One Bank ATM\nKawran Bazar").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(o_kawran, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(o_lalbag).title("One Bank ATM\nLalbag").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(o_lalbag, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(o_dak).title("One Bank ATM\nDakshin Basabo").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(o_dak, 11));

                        } else if (spinner.getSelectedItemPosition() == 15) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(se_rangan).title("SouthEast Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_rangan, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(se_moti).title("SouthEast Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_moti, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(se_dhk3).title("SouthEast Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_dhk3, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(se_shahid).title("SouthEast Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_shahid, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(se_north).title("SouthEast Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_north, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(se_purabi).title("SouthEast Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_purabi, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(se_dhk4).title("SouthEast Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_dhk4, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(se_panthapath).title("SouthEast Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_panthapath, 11));

                        } else if (spinner.getSelectedItemPosition() == 16) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(hk_dhk3).title("The HongKong and Shanghai Bank ATM\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hk_dhk3, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(hk_gul).title("The HongKong and Shanghai Bank ATM\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hk_gul, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(hk_bir).title("The HongKong and Shanghai Bank ATM\nBir Uttam Mir Shakhawat Sarak").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hk_bir, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(hk_bir_uttam).title("The HongKong and Shanghai Bank ATM\nBir Uttam CR Datta Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hk_bir_uttam, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(hk_bashu).title("The HongKong and Shanghai Bank ATM\nBashundhara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hk_bashu, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(hk_water).title("The HongKong and Shanghai Bank ATM\nWater Works Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hk_water, 11));

                        } else if (spinner.getSelectedItemPosition() == 17) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(st_zoo).title("Standard Bank ATM\nZoo Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(st_zoo, 11));

                        } else if (spinner.getSelectedItemPosition() == 18) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(tr_canto).title("Trust Bank ATM\nCantonment").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_canto, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(tr_shahed).title("Trust Bank ATM\nShaheed Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_shahed, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(tr_saudi).title("Trust Bank ATM\nSaudi Colony").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_saudi, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(tr_falcon).title("Trust Bank ATM\nFalcon Hall Adjacent").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_falcon, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(tr_rajni).title("Trust Bank ATM\nRajnigandha").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_rajni, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(tr_south).title("Trust Bank ATM\nSouth Gate Naval HQ").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_south, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(tr_navy).title("Trust Bank ATM\nNavy Head Quarter").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_navy, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(tr_army).title("Trust Bank ATM\nArmy Head Quarter").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_army, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(tr_cmh).title("Trust Bank ATM\nCMH Hospital").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_cmh, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(tr_prese).title("Trust Bank ATM\nGono Bhaban").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_prese, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(tr_bhan).title("Trust Bank ATM\nBhasantek").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_bhan, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(tr_apollo).title("Trust Bank ATM\nApollo Hospital Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_apollo, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(tr_dhan).title("Trust Bank ATM\nDhanmondi").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_dhan, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(tr_moham).title("Trust Bank ATM\nMohammadpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_moham, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(tr_kak).title("Trust Bank ATM\nKakrail").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_kak, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(tr_sat).title("Trust Bank ATM\nSatmosjid Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_sat, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(tr_mir).title("Trust Bank ATM\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_mir, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(tr_north).title("Trust Bank ATM\nPilkhana").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_north, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(tr_moti).title("Trust Bank ATM\nMotijheel").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_moti, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(tr_polashi).title("Trust Bank ATM\nAzimpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_polashi, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(tr_new).title("Trust Bank ATM\nNew Market").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_new, 11));

                        } else if (spinner.getSelectedItemPosition() == 19) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(ut_bir_uttam).title("Uttara Bank ATM\nBir Uttam Rafiqul Islam Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_bir_uttam, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ut_shanti).title("Uttara Bank ATM\nShanti Nagar").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_shanti, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ut_dar).title("Uttara Bank ATM\nDar-Us-Salam Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_dar, 11));

                        } else if (spinner.getSelectedItemPosition() == 20) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(i_gulshan).title("Islami Bank Bangladesh ATM\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_gulshan, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(i_bir_uttam_ave).title("Islami Bank Bangladesh ATM\nBir Uttam Rafiqul Islam Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_bir_uttam_ave, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(i_eskar).title("Islami Bank Bangladesh ATM\nNew Eskaton Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_eskar, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(i_kazi).title("Islami Bank Bangladesh ATM\nKazi Nazrul Islam Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_kazi, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(i_bashud).title("Islami Bank Bangladesh ATM\nBashundhara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_bashud, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(i_manikdi).title("Islami Bank Bangladesh ATM\nManikdi Bazar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_manikdi, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(i_mir).title("Islami Bank Bangladesh ATM\nMirpur Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_mir, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(i_shahedd).title("Islami Bank Bangladesh ATM\nShaheed Tajuddin Ahmed Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_shahedd, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(i_mazar_road).title("Islami Bank Bangladesh ATM\nMazar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_mazar_road, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(i_dhk1).title("Islami Bank Bangladesh ATM\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_dhk1, 11));

                        } else if (spinner.getSelectedItemPosition() == 21) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(j_sonar).title("Jamuna Bank ATM\nSonargaon").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_sonar, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(j_ground).title("Jamuna Bank ATM\nAli Bhaban").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_ground, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(j_dil).title("Jamuna Bank ATM\nDilkhusha Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_dil, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(j_dit).title("Jamuna Bank ATM\nDIT Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_dit, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(j_bir_ut).title("Jamuna Bank ATM\nBir Uttam Samsul Alam Sarak").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_bir_ut, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(j_ground_floor).title("Jamuna Bank ATM\nShahjahanpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_ground_floor, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(j_cir).title("Jamuna Bank ATM\nCirculer Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_cir, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(j_rankin).title("Jamuna Bank ATM\nRankin Street").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_rankin, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(j_dhk3).title("Jamuna Bank ATM\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_dhk3, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(j_mirp).title("Jamuna Bank ATM\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_mirp, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(j_dar1).title("Jamuna Bank ATM\nDar-Us-Salam Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_dar1, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(j_milk).title("Jamuna Bank ATM\nMilk Vita Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_milk, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(j_s_kafrul).title("Jamuna Bank ATM\nKafrul").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_s_kafrul, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(j_mohak).title("Jamuna Bank ATM\nMohakhali").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_mohak, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(j_gul1).title("Jamuna Bank ATM\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_gul1, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(j_gausul).title("Jamuna Bank ATM\nGausul Azam Ave ").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_gausul, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(j_dhk_mym).title("Jamuna Bank ATM\nDhaka Mymensingh Highway").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_dhk_mym, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(j_sat).title("Jamuna Bank ATM\nSatmosjid Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_sat, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(j_atish).title("Jamuna Bank ATM\nAtish Deepenkar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_atish, 11));

                        } else if (spinner.getSelectedItemPosition() == 22) {
                            mMap.clear();
                            marker = mMap.addMarker(new MarkerOptions().position(na_gulsh).title("National Credit and Commerce Bank ATM\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_gulsh, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(na_bbtoa).title("National Credit and Commerce Bank ATM\nBBTOA Building").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_bbtoa, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(na_mirpu).title("National Credit and Commerce Bank ATM\nBBTOA BUilding").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_mirpu, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(na_ram).title("National Credit and Commerce Bank ATM\nRam Krishna Mission").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_ram, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(na_dhk2).title("National Credit and Commerce Bank ATM\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_dhk2, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(na_school).title("National Credit and Commerce Bank ATM\nSchool Super Market").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_school, 11));

                        }
                        //}
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

    public void all_bank() {
        mMap.clear();
        spinner2.setSelection(adapter2.getCount());
        marker = mMap.addMarker(new MarkerOptions().position(b_gul).title("Brac Bank\nGulshan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_gul, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_ngul).title("Brac Bank\nNorth Gulshan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_ngul, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_banshundhara).title("Brac Bank\nBashundhara Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_banshundhara, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_asad).title("Brac Bank\nAsad Gate Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_asad, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_banani).title("Brac Bank\nBanani Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_banani, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_ashkona).title("Brac Bank\nAshkona Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_ashkona, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_samasjid).title("Brac Bank\nSatmosjod Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_samasjid, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_elephant).title("Brac Bank\nElephant Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_elephant, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_eskaton).title("Brac Bank\nEskaton").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_eskaton, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_mirpur).title("Brac Bank\nMirpur Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_mirpur, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_begum).title("Brac Bank\nBegum Rokeya Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_begum, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_mogbazar).title("Brac Bank\nMogbazar Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_mogbazar, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_motijheel).title("Brac Bank\nMotijheel Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_motijheel, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_notun).title("Brac Bank\nNatun Bazar Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_notun, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_banasree).title("Brac Bank\nBanasree").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_banasree, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_shymoli).title("Brac Bank\nShymoli Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_shymoli, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_dakk).title("Brac Bank\nDakkhin Khan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_dakk, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_shantinagar).title("Brac Bank\nShantinagar Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_shantinagar, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_uttra).title("Brac Bank\nUttara Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_uttra, 11));
        //atm
        marker = mMap.addMarker(new MarkerOptions().position(b_kamal).title("Brac Bank ATM\nKamal Ataturk Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_kamal, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_banani_1).title("Brac Bank ATM\nBanani").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_banani_1, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_agargoan).title("Brac Bank ATM\nAgargaon").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_agargoan, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_farm).title("Brac Bank ATM\nFarmgate").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_farm, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_azim).title("Brac Bank ATM\nAzimpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_azim, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_gulatm).title("Brac Bank ATM\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_gulatm, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_prag).title("Brac Bank ATM\nPragati Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_prag, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_kac).title("Brac Bank ATM\nKachukhet").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_kac, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_khilkhet).title("Brac Bank ATM\nKhilkhet").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_khilkhet, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_bir).title("Brac Bank ATM\nBir Uttam Rafiqul Islam Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_bir, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_air).title("Brac Bank ATM\nAirport Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_air, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_ibra).title("Brac Bank ATM\nIbrahimpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_ibra, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_sha).title("Brac Bank ATM\nShaheed Tajuddin Ahmed Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_sha, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_sonar).title("Brac Bank ATM\nSonargaon").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_sonar, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_new).title("Brac Bank ATM\nNew Eskaton").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_new, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_khil).title("Brac Bank ATM\nKhilgaon Taltola").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_khil, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_aftab).title("Brac Bank ATM\nAftab Nagar").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_aftab, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_kazi).title("Brac Bank ATM\nKazi Nazrul Islam Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_kazi, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_pant).title("Brac Bank ATM\nPanthapath").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_pant, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_begum_rokeya).title("Brac Bank ATM\nBegum Rokeya Avenue").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_begum_rokeya, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_moti).title("Brac Bank ATM\nMotijheel").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_moti, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_indi).title("Brac Bank ATM\nIndira Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_indi, 11));
        marker = mMap.addMarker(new MarkerOptions().position(b_rampura).title("Brac Bank ATM\nRampura DIT Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b_rampura, 11));

        marker = mMap.addMarker(new MarkerOptions().position(c_dil).title("City Bank\nDilkhusha Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_dil, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_banani).title("City Bank\nBanani Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_banani, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_dhan).title("City Bank\nDhanmondi 27 Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_dhan, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_kar).title("City Bank\nKarwanbazar Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_kar, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_mirpu).title("City Bank\nMirpur Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_mirpu, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_mog).title("City Bank\nMogbazar Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_mog, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_moti).title("City Bank\nMotijheel Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_moti, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_kakra).title("City Bank\nKakrail Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_kakra, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_dhan_2).title("City Bank\nDhanmondi 2 Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_dhan_2, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_pall).title("City Bank\nPallabi Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_pall, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_bad).title("City Bank\nBadda Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_bad, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_shy).title("City Bank\nShymoli Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_shy, 11));
        //atm
        marker = mMap.addMarker(new MarkerOptions().position(c_dilkhusa).title("City Bank ATM\nDilkhusha").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_dilkhusa, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_rif).title("City Bank ATM\nRiffle Squre").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_rif, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_pant).title("City Bank ATM\nPanthapath").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_pant, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_gre).title("City Bank ATM\nGreen Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_gre, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_wari).title("City Bank ATM\nWari").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_wari, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_ban).title("City Bank ATM\nBanani").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_ban, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_moh).title("City Bank ATM\nMohakhali").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_moh, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_baily).title("City Bank ATM\nBaily Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_baily, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_sat).title("City Bank ATM\nSatmosjid Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_sat, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_moni).title("City Bank ATM\nMonipuripara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_moni, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_pal).title("City Bank ATM\nPallabi").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_pal, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_mogb).title("City Bank ATM\nMogbazar").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_mogb, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_rok).title("City Bank ATM\nRokeya Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_rok, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_bana).title("City Bank ATM\nBanasree").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_bana, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_khil).title("City Bank ATM\nKhilgaon").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_khil, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_gul).title("City Bank ATM\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_gul, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_utt).title("City Bank ATM\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_utt, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_seg).title("City Bank ATM\nSegun Bagicha").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_seg, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_mir).title("City Bank ATM\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_mir, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_west).title("City Bank ATM\nRampura").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_west, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_dar).title("City Bank ATM\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_dar, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_bash).title("City Bank ATM\nBashundhara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_bash, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_fak).title("City Bank ATM\nFakirapool").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_fak, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_kam).title("City Bank ATM\nKamlapur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_kam, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_jan).title("City Bank ATM\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_jan, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_ele).title("City Bank ATM\nElephant Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_ele, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_shah).title("City Bank ATM\nShahjadpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_shah, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_dhanmondi).title("City Bank ATM\nDhanmondi").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_dhanmondi, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_uttara).title("City Bank ATM\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_uttara, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_mirpur).title("City Bank ATM\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_mirpur, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_elephant).title("City Bank ATM\nNew Elephant Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_elephant, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_mirpur_rup).title("City Bank ATM\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_mirpur_rup, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_kak).title("City Bank ATM\nKakrail").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_kak, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_dhaka).title("City Bank ATM\nDhaka Stoke Exchange").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_dhaka, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_rabi).title("City Bank ATM\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_rabi, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_prag).title("City Bank ATM\nPragati Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_prag, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_merul).title("City Bank ATM\nMerul Badda").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_merul, 11));
        marker = mMap.addMarker(new MarkerOptions().position(c_nik).title("City Bank ATM\nNikunja").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c_nik, 11));

        marker = mMap.addMarker(new MarkerOptions().position(u_ban).title("United Commercial Bank\nBanani Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_ban, 11));
        marker = mMap.addMarker(new MarkerOptions().position(u_mad).title("United Commercial Bank\nMadani Avenue Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_mad, 11));
        marker = mMap.addMarker(new MarkerOptions().position(u_gul).title("United Commercial Bank\nGulshan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_gul, 11));
        marker = mMap.addMarker(new MarkerOptions().position(u_bas).title("United Commercial Bank\nBashundhara Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_bas, 11));
        marker = mMap.addMarker(new MarkerOptions().position(u_dhan).title("United Commercial Bank\nDhanmondi 27 Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_dhan, 11));
        marker = mMap.addMarker(new MarkerOptions().position(u_kat).title("United Commercial Bank\nKatabon Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_kat, 11));
        marker = mMap.addMarker(new MarkerOptions().position(u_gulshan).title("United Commercial Bank\nGulshan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_gulshan, 11));
        marker = mMap.addMarker(new MarkerOptions().position(u_kar).title("United Commercial Bank\nKarwanbazar Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_kar, 11));
        marker = mMap.addMarker(new MarkerOptions().position(u_esk).title("United Commercial Bank\nEskaton Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_esk, 11));
        marker = mMap.addMarker(new MarkerOptions().position(u_aftab).title("United Commercial Bank\nAftabnagar Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_aftab, 11));
        marker = mMap.addMarker(new MarkerOptions().position(u_sidd).title("United Commercial Bank\nSiddeswari Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_sidd, 11));
        marker = mMap.addMarker(new MarkerOptions().position(u_sha).title("United Commercial Bank\nShahid syed Nazrul Islam Avenue Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_sha, 11));
        marker = mMap.addMarker(new MarkerOptions().position(u_moti).title("United Commercial Bank\nMotijheel Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_moti, 11));
        //atm
        marker = mMap.addMarker(new MarkerOptions().position(u_bash).title("United Commercial ATM\nBashundhara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_bash, 11));
        marker = mMap.addMarker(new MarkerOptions().position(u_gus).title("United Commercial ATM\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_gus, 11));
        marker = mMap.addMarker(new MarkerOptions().position(u_ring).title("United Commercial ATM\nRing Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_ring, 11));
        marker = mMap.addMarker(new MarkerOptions().position(u_asad).title("United Commercial ATM\nAsad Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_asad, 11));
        marker = mMap.addMarker(new MarkerOptions().position(u_kal).title("United Commercial ATM\nKalabagan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_kal, 11));
        marker = mMap.addMarker(new MarkerOptions().position(u_dhanmo).title("United Commercial ATM\nDhanmondi").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_dhanmo, 11));
        marker = mMap.addMarker(new MarkerOptions().position(u_bir).title("United Commercial ATM\nBir Uttam Mir Shakwat Ali Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_bir, 11));
        marker = mMap.addMarker(new MarkerOptions().position(u_lal).title("United Commercial ATM\nLalbagh").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_lal, 11));
        marker = mMap.addMarker(new MarkerOptions().position(u_pant).title("United Commercial ATM\nPanthapath").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_pant, 11));
        marker = mMap.addMarker(new MarkerOptions().position(u_new).title("United Commercial ATM\nNew Elephant Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_new, 11));
        marker = mMap.addMarker(new MarkerOptions().position(u_motijheel).title("United Commercial ATM\nMotijheel").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(u_motijheel, 11));

        marker = mMap.addMarker(new MarkerOptions().position(d_sonar).title("Dutch Bangla Bank\nSonargaon Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_sonar, 11));
        marker = mMap.addMarker(new MarkerOptions().position(d_dit).title("Dutch Bangla Bank\nDIT Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_dit, 11));
        marker = mMap.addMarker(new MarkerOptions().position(d_kazi).title("Dutch Bangla Bank\nKazi nazrul Islam Avenue Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_kazi, 11));
        marker = mMap.addMarker(new MarkerOptions().position(d_shanti).title("Dutch Bangla Bank\nShanti Nagar Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_shanti, 11));
        marker = mMap.addMarker(new MarkerOptions().position(d_ash).title("Dutch Bangla Bank\nAshkona Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_ash, 11));
        marker = mMap.addMarker(new MarkerOptions().position(d_khil).title("Dutch Bangla Bank\nKhilkhet Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_khil, 11));
        marker = mMap.addMarker(new MarkerOptions().position(d_ring).title("Dutch Bangla Bank\nRing Road Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_ring, 11));
        marker = mMap.addMarker(new MarkerOptions().position(d_ele).title("Dutch Bangla Bank\nElephant Road Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_ele, 11));
        marker = mMap.addMarker(new MarkerOptions().position(d_dil).title("Dutch Bangla Bank\nDilkhusha Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_dil, 11));
        marker = mMap.addMarker(new MarkerOptions().position(d_sat).title("Dutch Bangla Bank\nSatmosjid Road Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_sat, 11));
        //atm
        marker = mMap.addMarker(new MarkerOptions().position(d_cant).title("Dutch Bangla Bank ATM\nCantonment").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_cant, 11));
        marker = mMap.addMarker(new MarkerOptions().position(d_utt).title("Dutch Bangla Bank ATM\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_utt, 11));
        marker = mMap.addMarker(new MarkerOptions().position(d_vas).title("Dutch Bangla Bank ATM\nVashantek").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_vas, 11));
        marker = mMap.addMarker(new MarkerOptions().position(d_sha).title("Dutch Bangla Bank ATM\nShahid latif Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_sha, 11));
        marker = mMap.addMarker(new MarkerOptions().position(d_shah).title("Dutch Bangla Bank ATM\nShah Kabir Mazar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_shah, 11));
        marker = mMap.addMarker(new MarkerOptions().position(d_air).title("Dutch Bangla Bank ATM\nAirport").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_air, 11));
        marker = mMap.addMarker(new MarkerOptions().position(d_ashkona).title("Dutch Bangla Bank ATM\nAshkona").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_ashkona, 11));
        marker = mMap.addMarker(new MarkerOptions().position(d_gau).title("Dutch Bangla Bank ATM\nGausul Azam Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_gau, 11));
        marker = mMap.addMarker(new MarkerOptions().position(d_sonargoan).title("Dutch Bangla Bank ATM\nSonargaon").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_sonargoan, 11));
        marker = mMap.addMarker(new MarkerOptions().position(d_taj).title("Dutch Bangla Bank ATM\nTejgaon").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_taj, 11));
        marker = mMap.addMarker(new MarkerOptions().position(d_ram).title("Dutch Bangla Bank ATM\nRampura").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_ram, 11));
        marker = mMap.addMarker(new MarkerOptions().position(d_banasree).title("Dutch Bangla Bank ATM\nBanasree").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_banasree, 11));
        marker = mMap.addMarker(new MarkerOptions().position(d_khilkhet).title("Dutch Bangla Bank ATM\nKhilgaon").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_khilkhet, 11));
        marker = mMap.addMarker(new MarkerOptions().position(d_new).title("Dutch Bangla Bank ATM\nNew Eskaton Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_new, 11));
        marker = mMap.addMarker(new MarkerOptions().position(d_karwan).title("Dutch Bangla Bank ATM\nKarwanbazar").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_karwan, 11));
        marker = mMap.addMarker(new MarkerOptions().position(d_shantinagar).title("Dutch Bangla Bank ATM\nShantinagar").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_shantinagar, 11));
        marker = mMap.addMarker(new MarkerOptions().position(d_baily).title("Dutch Bangla Bank ATM\nBaily Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_baily, 11));
        marker = mMap.addMarker(new MarkerOptions().position(d_shahbag).title("Dutch Bangla Bank ATM\nShahbag").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_shahbag, 11));
        marker = mMap.addMarker(new MarkerOptions().position(d_moti).title("Dutch Bangla Bank ATM\nMotijheel").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_moti, 11));

        marker = mMap.addMarker(new MarkerOptions().position(h_dha).title("HSBC Bank\nDhanmondi Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(h_dha, 11));
        marker = mMap.addMarker(new MarkerOptions().position(h_gul).title("HSBC Bank\nGulshan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(h_gul, 11));
        marker = mMap.addMarker(new MarkerOptions().position(h_mir).title("HSBC Bank\nMirpur Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(h_mir, 11));
        marker = mMap.addMarker(new MarkerOptions().position(h_sonar).title("HSBC Bank\nSonargoan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(h_sonar, 11));
        //atm
        marker = mMap.addMarker(new MarkerOptions().position(h_ram).title("HSBC Bank ATM\nRampura").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(h_ram, 11));
        marker = mMap.addMarker(new MarkerOptions().position(h_gul2).title("HSBC Bank ATM\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(h_gul2, 11));
        marker = mMap.addMarker(new MarkerOptions().position(h_gul1).title("HSBC Bank ATM\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(h_gul1, 11));
        marker = mMap.addMarker(new MarkerOptions().position(h_bash).title("HSBC Bank ATM\nBashundhara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(h_bash, 11));
        marker = mMap.addMarker(new MarkerOptions().position(h_chak).title("HSBC Bank ATM\nChawbazar").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(h_chak, 11));

        marker = mMap.addMarker(new MarkerOptions().position(s_gul).title("Standard Chartered Bank\nGulshan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_gul, 11));
        marker = mMap.addMarker(new MarkerOptions().position(s_satm).title("Standard Chartered Bank\nSatmosjid Road Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_satm, 11));
        marker = mMap.addMarker(new MarkerOptions().position(s_dhan).title("Standard Chartered Bank\nDhanmondi Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_dhan, 11));
        marker = mMap.addMarker(new MarkerOptions().position(s_beg).title("Standard Chartered Bank\nBegum Rokeya Avenue Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_beg, 11));
        marker = mMap.addMarker(new MarkerOptions().position(s_banasree).title("Standard Chartered Bank\nBanasree Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_banasree, 11));
        marker = mMap.addMarker(new MarkerOptions().position(s_chaw).title("Standard Chartered Bank\nChawbazar Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_chaw, 11));
        marker = mMap.addMarker(new MarkerOptions().position(s_khil).title("Standard Chartered Bank\nKhilgaon Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_khil, 11));
        marker = mMap.addMarker(new MarkerOptions().position(s_utt).title("Standard Chartered Bank\nUttara Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_utt, 11));
        //atm
        marker = mMap.addMarker(new MarkerOptions().position(s_ring).title("Standard Chartered ATM\nRing Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_ring, 11));
        marker = mMap.addMarker(new MarkerOptions().position(s_begum).title("Standard Chartered ATM\nBegum Rokeya Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_begum, 11));
        marker = mMap.addMarker(new MarkerOptions().position(s_mog).title("Standard Chartered ATM\nMogbazar").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_mog, 11));
        marker = mMap.addMarker(new MarkerOptions().position(s_khilkhet).title("Standard Chartered ATM\nKhilkhet").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_khilkhet, 11));
        marker = mMap.addMarker(new MarkerOptions().position(s_bangla).title("Standard Chartered ATM\nBangla Academy Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_bangla, 11));
        marker = mMap.addMarker(new MarkerOptions().position(s_lal).title("Standard Chartered ATM\nLalbagh").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_lal, 11));
        marker = mMap.addMarker(new MarkerOptions().position(s_banani).title("Standard Chartered ATM\nBanani").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_banani, 11));
        marker = mMap.addMarker(new MarkerOptions().position(s_sat).title("Standard Chartered ATM\nSatmosjid Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_sat, 11));
        marker = mMap.addMarker(new MarkerOptions().position(s_dhanmon).title("Standard Chartered ATM\nDhanmondi").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_dhanmon, 11));
        marker = mMap.addMarker(new MarkerOptions().position(s_gulshan).title("Standard Chartered ATM\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_gulshan, 11));
        marker = mMap.addMarker(new MarkerOptions().position(s_gul1).title("Standard Chartered ATM\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_gul1, 11));
        marker = mMap.addMarker(new MarkerOptions().position(s_farm).title("Standard Chartered ATM\nFarmgate").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_farm, 11));
        marker = mMap.addMarker(new MarkerOptions().position(s_bashu).title("Standard Chartered ATM\nBashundhara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_bashu, 11));
        marker = mMap.addMarker(new MarkerOptions().position(s_agar).title("Standard Chartered ATM\nAgargaon").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_agar, 11));
        marker = mMap.addMarker(new MarkerOptions().position(s_dit).title("Standard Chartered ATM\nDIT Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_dit, 11));
        marker = mMap.addMarker(new MarkerOptions().position(s_gre).title("Standard Chartered ATM\nGreen Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_gre, 11));
        marker = mMap.addMarker(new MarkerOptions().position(s_new).title("Standard Chartered ATM\nNew Baily Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_new, 11));
        marker = mMap.addMarker(new MarkerOptions().position(s_segu).title("Standard Chartered ATM\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_segu, 11));
        marker = mMap.addMarker(new MarkerOptions().position(s_uttara).title("Standard Chartered ATM\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s_uttara, 11));

        marker = mMap.addMarker(new MarkerOptions().position(p_ban).title("Prime Bank\nBanani Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_ban, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_gul).title("Prime Bank\nGulshan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_gul, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_bas).title("Prime Bank\nBashundhara Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_bas, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_kach).title("Prime Bank\nKachukhet Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_kach, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_moh).title("Prime Bank\nMohakhali Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_moh, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_tej).title("Prime Bank\nTejgaon Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_tej, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_tejturi).title("Prime Bank\nTejturi Bazar Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_tejturi, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_mid).title("Prime Bank\nMiddle Badda Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_mid, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_dhanmond).title("Prime Bank\nDhanmondi Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_dhanmond, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_asad).title("Prime Bank\nAsad Avenue Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_asad, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_sat).title("Prime Bank\nSatmosjid Road Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_sat, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_new).title("Prime Bank\nNew Eskaton Road Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_new, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_ele).title("Prime Bank\nElephant Road Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_ele, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_beg).title("Prime Bank\nBegum Rokeya Sarani Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_beg, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_banasree).title("Prime Bank\nBanasree Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_banasree, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_ring).title("Prime Bank\nRing Road Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_ring, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_mali).title("Prime Bank\nMalibag Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_mali, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_seg).title("Prime Bank\nSegun Bagicha Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_seg, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_mir).title("Prime Bank\nMirpur Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_mir, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_moti).title("Prime Bank\nMotijheel Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_moti, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_south).title("Prime Bank\nSouth Bashabo Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_south, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_dil).title("Prime Bank\nDilkhusha Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_dil, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_dit).title("Prime Bank\nDIT Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_dit, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_lal).title("Prime Bank\nLalbag Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_lal, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_utt).title("Prime Bank\nUttara Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_utt, 11));
        //atm
        marker = mMap.addMarker(new MarkerOptions().position(p_taj).title("Prime Bank ATM\nTajgaon").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_taj, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_bij).title("Prime Bank ATM\nBijoy Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_bij, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_pant).title("Prime Bank ATM\nPanthapath").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_pant, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_satmas).title("Prime Bank ATM\nSatmosjid Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_satmas, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_ram).title("Prime Bank ATM\nRampura").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_ram, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_elephant).title("Prime Bank ATM\nElephant Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_elephant, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_tajmahal).title("Prime Bank ATM\nTajmahal Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_tajmahal, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_shy).title("Prime Bank ATM\nShymoli").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_shy, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_dhanmondi).title("Prime Bank ATM\nDhanmondi").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_dhanmondi, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_begu).title("Prime Bank ATM\nBegum Rokeya Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_begu, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_ramna).title("Prime Bank ATM\nRamna").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_ramna, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_lalbag).title("Prime Bank ATM\nLalbagh").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_lalbag, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_shidd).title("Prime Bank ATM\nShiddeswari Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_shidd, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_tal).title("Prime Bank ATM\nTaltola").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_tal, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_begum).title("Prime Bank ATM\nBegum Rokeya Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_begum, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_rup).title("Prime Bank ATM\nRupnagar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_rup, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_dilkhusa).title("Prime Bank ATM\nDilkhusha").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_dilkhusa, 11));
        marker = mMap.addMarker(new MarkerOptions().position(p_uttara).title("Prime Bank ATM\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_uttara, 11));

        marker = mMap.addMarker(new MarkerOptions().position(e_mir).title("Eastern Bank\nMirpur Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_mir, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_mirpu).title("Eastern Bank\nMirpur Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_mirpu, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_ban).title("Eastern Bank\nBanani Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_ban, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_utt).title("Eastern Bank\nUttara Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_utt, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_ring).title("Eastern Bank\nRing Road Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_ring, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_dak).title("Eastern Bank\nDakshin Khan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_dak, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_uttara).title("Eastern Bank\nUttara Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_uttara, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_uttara_gr).title("Eastern Bank\nUttara Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_uttara_gr, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_kal).title("Eastern Bank\nKalabagan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_kal, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_bash).title("Eastern Bank\nBashundhara Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_bash, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_gul2).title("Eastern Bank\nGulshan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_gul2, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_gulave).title("Eastern Bank\nGulshan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_gulave, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_sonargoan).title("Eastern Bank\nSonargaon Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_sonargoan, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_gula).title("Eastern Bank\nGulshan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_gula, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_mog).title("Eastern Bank\nMogbazar Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_mog, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_shan).title("Eastern Bank\nShantinagar Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_shan, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_kak).title("Eastern Bank\nKakrail Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_kak, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_purana).title("Eastern Bank\nPurana Paltan Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_purana, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_pil).title("Eastern Bank\nPilkhana Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_pil, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_shant).title("Eastern Bank\nShanti Nagar Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_shant, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_north).title("Eastern Bank\nNorth South Branch").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_north, 11));
        //atm
        marker = mMap.addMarker(new MarkerOptions().position(e_rupnagar).title("Eastern Bank ATM\nRupnagar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_rupnagar, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_uttara).title("Eastern Bank ATM\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_uttara, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_moh).title("Eastern Bank ATM\nMohammadpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_moh, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_azam).title("Eastern Bank ATM\nAzampur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_azam, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_shmoli).title("Eastern Bank ATM\nShymoli").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_shmoli, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_moha).title("Eastern Bank ATM\nMohammadi Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_moha, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_tajmohol).title("Eastern Bank ATM\nTajmohol Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_tajmohol, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_gulshan).title("Eastern Bank ATM\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_gulshan, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_new).title("Eastern Bank ATM\nNew Airport Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_new, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_uttara_garib).title("Eastern Bank ATM\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_uttara_garib, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_azad).title("Eastern Bank ATM\nAsad Gate").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_azad, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_bijoy).title("Eastern Bank ATM\nBijoy Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_bijoy, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_mohakhali).title("Eastern Bank ATM\nMohakhali").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_mohakhali, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_farmgate).title("Eastern Bank ATM\nFarmgate").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_farmgate, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_shi).title("Eastern Bank ATM\nShimanto Square").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_shi, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_mirpur).title("Eastern Bank ATM\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_mirpur, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_karwan).title("Eastern Bank ATM\nKarwanbazar").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_karwan, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_elep).title("Eastern Bank ATM\nElephant Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_elep, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_gulshan_nav).title("Eastern Bank ATM\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_gulshan_nav, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_moni).title("Eastern Bank ATM\nMonipuripara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_moni, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_pant).title("Eastern Bank ATM\nPanthapath").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_pant, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_new_b).title("Eastern Bank ATM\nNew Baily Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_new_b, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_shidd).title("Eastern Bank ATM\nShiddheswari Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_shidd, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_shanr).title("Eastern Bank ATM\nShantinagar").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_shanr, 11));
        marker = mMap.addMarker(new MarkerOptions().position(e_dilkh).title("Eastern Bank ATM\nDilkhusha").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(e_dilkh, 11));

        marker = mMap.addMarker(new MarkerOptions().position(ab_dhan).title("AB Bank Limited\nDhanmondi").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_dhan, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ab_gul).title("AB Bank Limited\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_gul, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ab_vip).title("AB Bank Limited\nVIP Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_vip, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ab_kazi).title("AB Bank Limited\nKazi Nazrul Islam Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_kazi, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ab_mali).title("AB Bank Limited\nMalibagh").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_mali, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ab_mir).title("AB Bank Limited\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_mir, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ab_dit).title("AB Bank Limited\nDIT Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_dit, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ab_moha).title("AB Bank Limited\nMohakhali").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_moha, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ab_bari).title("AB Bank Limited\nBaridhara").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_bari, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ab_she).title("AB Bank Limited\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_she, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ab_shymoli).title("AB Bank Limited\nShymoli").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_shymoli, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ab_uttara).title("AB Bank Limited\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_uttara, 11));
        //atm
        marker = mMap.addMarker(new MarkerOptions().position(ab_dhanmondi).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_dhanmondi, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ab_dhanmondi_1).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_dhanmondi_1, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ab_dhanmondi_2).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_dhanmondi_2, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ab_dhanmondi_3).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_dhanmondi_3, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ab_dhanmondi_4).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_dhanmondi_4, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ab_dhanmondi_5).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_dhanmondi_5, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ab_ban).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_ban, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ab_baridhara).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_baridhara, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ab_gulsh).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_gulsh, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ab_south_gul).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_south_gul, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ab_ring).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_ring, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ab_gulshan).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_gulshan, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ab_kak).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_kak, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ab_kazi_nazrul).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_kazi_nazrul, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ab_malibagh_1).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_malibagh_1, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ab_bashu).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_bashu, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ab_manik).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_manik, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ab_mirp).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_mirp, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ab_mir_1).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_mir_1, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ab_mir_2).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_mir_2, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ab_mir_rup).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_mir_rup, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ab_mir_sec).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_mir_sec, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ab_utt).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_utt, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ab_dak).title("AB Bank ATM\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ab_dak, 11));

        marker = mMap.addMarker(new MarkerOptions().position(bn_bashu).title("Bank Asia\nBashundhara").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_bashu, 11));
        marker = mMap.addMarker(new MarkerOptions().position(bn_sonar).title("Bank Asia\nSonargoan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_sonar, 11));
        marker = mMap.addMarker(new MarkerOptions().position(bn_dak).title("Bank Asia\nDakshinkhan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_dak, 11));
        marker = mMap.addMarker(new MarkerOptions().position(bn_meher).title("Bank Asia\nMeher Plaza").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_meher, 11));
        marker = mMap.addMarker(new MarkerOptions().position(bn_ele).title("Bank Asia\nElephant Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_ele, 11));
        marker = mMap.addMarker(new MarkerOptions().position(bn_eska).title("Bank Asia\nEskaton").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_eska, 11));
        marker = mMap.addMarker(new MarkerOptions().position(bn_gul).title("Bank Asia\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_gul, 11));
        marker = mMap.addMarker(new MarkerOptions().position(bn_ban).title("Bank Asia\nBanani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_ban, 11));
        marker = mMap.addMarker(new MarkerOptions().position(bn_dil).title("Bank Asia\nDilkhusha").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_dil, 11));
        marker = mMap.addMarker(new MarkerOptions().position(bn_pallabi).title("Bank Asia\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_pallabi, 11));
        marker = mMap.addMarker(new MarkerOptions().position(bn_moha).title("Bank Asia\nMohakhali").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_moha, 11));
        marker = mMap.addMarker(new MarkerOptions().position(bn_pal).title("Bank Asia\nPurana Paltan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_pal, 11));
        marker = mMap.addMarker(new MarkerOptions().position(bn_moti).title("Bank Asia\nMotijheel").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_moti, 11));
        marker = mMap.addMarker(new MarkerOptions().position(bn_progati).title("Bank Asia\nProgoti Soroni").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_progati, 11));
        marker = mMap.addMarker(new MarkerOptions().position(bn_ring).title("Bank Asia\nRing Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_ring, 11));
        marker = mMap.addMarker(new MarkerOptions().position(bn_rup_mir).title("Bank Asia\nRupnagar Mirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_rup_mir, 11));
        marker = mMap.addMarker(new MarkerOptions().position(bn_bijoy).title("Bank Asia\nBijoy Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_bijoy, 11));
        marker = mMap.addMarker(new MarkerOptions().position(bn_cir).title("Bank Asia\nCircular Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_cir, 11));
        marker = mMap.addMarker(new MarkerOptions().position(bn_shymoli).title("Bank Asia\nShymoli").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_shymoli, 11));
        marker = mMap.addMarker(new MarkerOptions().position(bn_taj).title("Bank Asia\nTajgaon").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_taj, 11));
        marker = mMap.addMarker(new MarkerOptions().position(bn_utt).title("Bank Asia\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_utt, 11));
        //atm
        marker = mMap.addMarker(new MarkerOptions().position(bn_bashundhara).title("Bank Asia ATM\nBashundhara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_bashundhara, 11));
        marker = mMap.addMarker(new MarkerOptions().position(bn_eskaton).title("Bank Asia ATM\nEskaton").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_eskaton, 11));
        marker = mMap.addMarker(new MarkerOptions().position(bn_sonargaon).title("Bank Asia ATM\nSonargaon").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_sonargaon, 11));
        marker = mMap.addMarker(new MarkerOptions().position(bn_dhanmond).title("Bank Asia ATM\nDhanmondi").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_dhanmond, 11));
        marker = mMap.addMarker(new MarkerOptions().position(bn_dilku).title("Bank Asia ATM\nDilkhusha").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_dilku, 11));
        marker = mMap.addMarker(new MarkerOptions().position(bn_garden).title("Bank Asia ATM\nEskaton").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_garden, 11));
        marker = mMap.addMarker(new MarkerOptions().position(bn_bays).title("Bank Asia ATM\nBays Galleria").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_bays, 11));
        marker = mMap.addMarker(new MarkerOptions().position(bn_sat).title("Bank Asia ATM\nSatmosjid Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_sat, 11));
        marker = mMap.addMarker(new MarkerOptions().position(bn_mirpur).title("Bank Asia ATM\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_mirpur, 11));
        marker = mMap.addMarker(new MarkerOptions().position(bn_moham).title("Bank Asia ATM\nMohammadpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_moham, 11));
        marker = mMap.addMarker(new MarkerOptions().position(bn_kamla).title("Bank Asia ATM\nKamlapur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_kamla, 11));
        marker = mMap.addMarker(new MarkerOptions().position(bn_paltan).title("Bank Asia ATM\nPaltan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_paltan, 11));
        marker = mMap.addMarker(new MarkerOptions().position(bn_ring_road).title("Bank Asia ATM\nRing Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_ring_road, 11));
        marker = mMap.addMarker(new MarkerOptions().position(bn_shym).title("Bank Asia ATM\nShymoli").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_shym, 11));
        marker = mMap.addMarker(new MarkerOptions().position(bn_nazrul_avenue).title("Bank Asia ATM\nKazi Nazrul Islam Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bn_nazrul_avenue, 11));

        marker = mMap.addMarker(new MarkerOptions().position(ex_pragati).title("Exim Bank\nPragati Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_pragati, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ex_gul).title("Exim Bank\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_gul, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ex_pantha).title("Exim Bank\nPanthapath").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_pantha, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ex_bgmea).title("Exim Bank\nPnthapath").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_bgmea, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ex_gul2).title("Exim Bank\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_gul2, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ex_rokeya).title("Exim Bank\nBegum Rokeya Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_rokeya, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ex_dit).title("Exim Bank\nDIT Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_dit, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ex_pra).title("Exim Bank\nPragati Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_pra, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ex_ele).title("Exim Bank\nElephant Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_ele, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ex_moti).title("Exim Bank\nMotijheel").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_moti, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ex_sat).title("Exim Bank\nSatmosjid Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_sat, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ex_rokeya_sarani).title("Exim Bank\nBegum Rokeya Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_rokeya_sarani, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ex_ring).title("Exim Bank\nRing Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_ring, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ex_nawabpur).title("Exim Bank\nNawabpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_nawabpur, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ex_garib).title("Exim Bank\nGarib-e-Newaz").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_garib, 11));
        //atm
        marker = mMap.addMarker(new MarkerOptions().position(ex_dhk).title("Exim Bank ATM\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_dhk, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ex_ring_road).title("Exim Bank ATM\nRing Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ex_ring_road, 11));

        marker = mMap.addMarker(new MarkerOptions().position(m_pragati).title("Mercantile Bank\nPragati Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_pragati, 11));
        marker = mMap.addMarker(new MarkerOptions().position(m_kachu).title("Mercantile Bank\nKachukhet").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_kachu, 11));
        marker = mMap.addMarker(new MarkerOptions().position(m_rokeya).title("Mercantile Bank\nBegum Rokeya Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_rokeya, 11));
        marker = mMap.addMarker(new MarkerOptions().position(m_gul).title("Mercantile Bank\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_gul, 11));
        marker = mMap.addMarker(new MarkerOptions().position(m_moha).title("Mercantile Bank\nMohakhali").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_moha, 11));
        marker = mMap.addMarker(new MarkerOptions().position(m_moha_bir).title("Mercantile Bank\nMohakhali").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_moha_bir, 11));
        marker = mMap.addMarker(new MarkerOptions().position(m_ring).title("Mercantile Bank\nRing Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_ring, 11));
        marker = mMap.addMarker(new MarkerOptions().position(m_dit).title("Mercantile Bank\nDIT Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_dit, 11));
        marker = mMap.addMarker(new MarkerOptions().position(m_dhaka).title("Mercantile Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_dhaka, 11));
        marker = mMap.addMarker(new MarkerOptions().position(m_pantha).title("Mercantile Bank\nPanthapath").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_pantha, 11));
        marker = mMap.addMarker(new MarkerOptions().position(m_kazi).title("Mercantile Bank\nKazi Nazrul Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_kazi, 11));
        marker = mMap.addMarker(new MarkerOptions().position(m_sat).title("Mercantile Bank\nSatmosjid Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_sat, 11));
        marker = mMap.addMarker(new MarkerOptions().position(m_cir).title("Mercantile Bank\nCirculer Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_cir, 11));
        marker = mMap.addMarker(new MarkerOptions().position(m_jaha).title("Mercantile Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_jaha, 11));
        marker = mMap.addMarker(new MarkerOptions().position(m_moulana).title("Mercantile Bank\nMoulana Vasani Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_moulana, 11));
        marker = mMap.addMarker(new MarkerOptions().position(m_sharani).title("Mercantile Bank\nShahid Syed Nazrul Islam Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_sharani, 11));
        marker = mMap.addMarker(new MarkerOptions().position(m_malek).title("Mercantile Bank\nMalek Mansion").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_malek, 11));
        marker = mMap.addMarker(new MarkerOptions().position(m_pass).title("Mercantile Bank\nPresident Pass").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_pass, 11));
        marker = mMap.addMarker(new MarkerOptions().position(m_moulo).title("Mercantile Bank\nMoulovibazar Dhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_moulo, 11));
        marker = mMap.addMarker(new MarkerOptions().position(m_lal).title("Mercantile Bank\nLal Mohan Shaha Street").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_lal, 11));
        marker = mMap.addMarker(new MarkerOptions().position(m_indira).title("Mercantile Bank\nIndira Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_indira, 11));
        marker = mMap.addMarker(new MarkerOptions().position(m_asad).title("Mercantile Bank\nAsad Gate").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_asad, 11));
        marker = mMap.addMarker(new MarkerOptions().position(m_manikdi).title("Mercantile Bank\nManikdi Bazar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_manikdi, 11));
        marker = mMap.addMarker(new MarkerOptions().position(m_ibra).title("Mercantile Bank\nIbrahimpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_ibra, 11));
        marker = mMap.addMarker(new MarkerOptions().position(m_shahid).title("Mercantile Bank\nShahid Latif Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m_shahid, 11));

        marker = mMap.addMarker(new MarkerOptions().position(mu_pragati).title("Mutual Trust Bank\nPragati Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_pragati, 11));
        marker = mMap.addMarker(new MarkerOptions().position(mu_dhk).title("Mutual Trust Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_dhk, 11));
        marker = mMap.addMarker(new MarkerOptions().position(mu_dhk1).title("Mutual Trust Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_dhk1, 11));
        marker = mMap.addMarker(new MarkerOptions().position(mu_gul).title("Mutual Trust Bank\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_gul, 11));
        marker = mMap.addMarker(new MarkerOptions().position(mu_mir).title("Mutual Trust Bank\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_mir, 11));
        marker = mMap.addMarker(new MarkerOptions().position(mu_dhk2).title("Mutual Trust Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_dhk2, 11));
        marker = mMap.addMarker(new MarkerOptions().position(mu_bir).title("Mutual Trust Bank\nBir Uttam Rafiqul Islam Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_bir, 11));
        marker = mMap.addMarker(new MarkerOptions().position(mu_gulshan).title("Mutual Trust Bank\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_gulshan, 11));
        marker = mMap.addMarker(new MarkerOptions().position(mu_dhk3).title("Mutual Trust Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_dhk3, 11));
        marker = mMap.addMarker(new MarkerOptions().position(mu_bir_uttam).title("Mutual Trust Bank\nBir Uttam Mir Shawkat Sarak").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_bir_uttam, 11));
        marker = mMap.addMarker(new MarkerOptions().position(mu_service).title("Mutual Trust Bank\nService Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_service, 11));
        marker = mMap.addMarker(new MarkerOptions().position(mu_dhk4).title("Mutual Trust Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_dhk4, 11));
        marker = mMap.addMarker(new MarkerOptions().position(mu_shah).title("Mutual Trust Bank\nShah Mokhdum Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_shah, 11));
        marker = mMap.addMarker(new MarkerOptions().position(mu_moha).title("Mutual Trust Bank\nMohammadpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_moha, 11));
        marker = mMap.addMarker(new MarkerOptions().position(mu_hatir).title("Mutual Trust Bank\nHatirjheel").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_hatir, 11));
        marker = mMap.addMarker(new MarkerOptions().position(mu_chandra).title("Mutual Trust Bank\nChandrashila Suvastu Tower").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_chandra, 11));
        marker = mMap.addMarker(new MarkerOptions().position(mu_dhk5).title("Mutual Trust Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_dhk5, 11));
        marker = mMap.addMarker(new MarkerOptions().position(mu_ele).title("Mutual Trust Bank\nElephant Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_ele, 11));
        marker = mMap.addMarker(new MarkerOptions().position(mu_uttara).title("Mutual Trust Bank\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_uttara, 11));
        marker = mMap.addMarker(new MarkerOptions().position(mu_samsul).title("Mutual Trust Bank\nBir Uttam Samsul Alam Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_samsul, 11));
        marker = mMap.addMarker(new MarkerOptions().position(mu_central).title("Mutual Trust Bank\nCentral Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_central, 11));
        marker = mMap.addMarker(new MarkerOptions().position(mu_moti).title("Mutual Trust Bank\nMotijheel").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_moti, 11));
        marker = mMap.addMarker(new MarkerOptions().position(mu_ramna).title("Mutual Trust Bank\nRamna").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_ramna, 11));
        marker = mMap.addMarker(new MarkerOptions().position(mu_dil).title("Mutual Trust Bank\nDilkhusha").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_dil, 11));
        marker = mMap.addMarker(new MarkerOptions().position(mu_motijheel).title("Mutual Trust Bank\nMotijheel").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_motijheel, 11));
        marker = mMap.addMarker(new MarkerOptions().position(mu_kazi).title("Mutual Trust Bank\nKazi Nazrul Islam Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_kazi, 11));
        marker = mMap.addMarker(new MarkerOptions().position(mu_chowk).title("Mutual Trust Bank\nLalbag").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mu_chowk, 11));

        marker = mMap.addMarker(new MarkerOptions().position(pr_gul).title("The Premier Bank\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pr_gul, 11));
        marker = mMap.addMarker(new MarkerOptions().position(pr_kemal).title("The Premier Bank\nKemal Ataturk Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pr_kemal, 11));
        marker = mMap.addMarker(new MarkerOptions().position(pr_dhk1).title("The Premier Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pr_dhk1, 11));
        marker = mMap.addMarker(new MarkerOptions().position(pr_bir).title("The Premier Bank\nBir Uttam AK Khandakar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pr_bir, 11));
        marker = mMap.addMarker(new MarkerOptions().position(pr_mir).title("The Premier Bank\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pr_mir, 11));
        marker = mMap.addMarker(new MarkerOptions().position(pr_begum).title("The Premier Bank\nBegum Rokeya Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pr_begum, 11));
        marker = mMap.addMarker(new MarkerOptions().position(pr_agrani).title("The Premier Bank\nAgrani Midtown Complex").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pr_agrani, 11));
        marker = mMap.addMarker(new MarkerOptions().position(pr_ring).title("The Premier Bank\nRing Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pr_ring, 11));
        marker = mMap.addMarker(new MarkerOptions().position(pr_new).title("The Premier Bank\nNew Market").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pr_new, 11));
        marker = mMap.addMarker(new MarkerOptions().position(pr_vip).title("The Premier Bank\nVIP Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pr_vip, 11));
        marker = mMap.addMarker(new MarkerOptions().position(pr_air).title("The Premier Bank\nAirport").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pr_air, 11));
        marker = mMap.addMarker(new MarkerOptions().position(pr_dhk2).title("The Premier Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pr_dhk2, 11));
        marker = mMap.addMarker(new MarkerOptions().position(pr_momen).title("The Premier Bank\nMomenbug").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pr_momen, 11));

        marker = mMap.addMarker(new MarkerOptions().position(o_kak).title("One Bank\nKakrail").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(o_kak, 11));
        marker = mMap.addMarker(new MarkerOptions().position(o_alam).title("One Bank\nAlam Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(o_alam, 11));
        marker = mMap.addMarker(new MarkerOptions().position(o_lal).title("One Bank\nLalbagh").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(o_lal, 11));
        marker = mMap.addMarker(new MarkerOptions().position(o_al).title("One Bank\nAL Kauser Palace").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(o_al, 11));
        marker = mMap.addMarker(new MarkerOptions().position(o_selina).title("One Bank\nShahid Shagbadik Selina Parvin Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(o_selina, 11));
        marker = mMap.addMarker(new MarkerOptions().position(o_dhk1).title("One Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(o_dhk1, 11));
        //atm
        marker = mMap.addMarker(new MarkerOptions().position(o_dil).title("One Bank ATM\nDilkhusha").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(o_dil, 11));
        marker = mMap.addMarker(new MarkerOptions().position(o_kawran).title("One Bank ATM\nKawran Bazar").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(o_kawran, 11));
        marker = mMap.addMarker(new MarkerOptions().position(o_lalbag).title("One Bank ATM\nLalbag").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(o_lalbag, 11));
        marker = mMap.addMarker(new MarkerOptions().position(o_dak).title("One Bank ATM\nDakshin Basabo").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(o_dak, 11));

        marker = mMap.addMarker(new MarkerOptions().position(se_simpl).title("SouthEast Bank\nSimpletree").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_simpl, 11));
        marker = mMap.addMarker(new MarkerOptions().position(se_begum).title("SouthEast Bank\nBegum Rokeya Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_begum, 11));
        marker = mMap.addMarker(new MarkerOptions().position(se_dhk1).title("SouthEast Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_dhk1, 11));
        marker = mMap.addMarker(new MarkerOptions().position(se_bir).title("SouthEast Bank\nBir Uttam AK Khandakar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_bir, 11));
        marker = mMap.addMarker(new MarkerOptions().position(se_gul).title("SouthEast Bank\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_gul, 11));
        marker = mMap.addMarker(new MarkerOptions().position(se_islampur).title("SouthEast Bank\nIslampur").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_islampur, 11));
        marker = mMap.addMarker(new MarkerOptions().position(se_eska).title("SouthEast Bank\nEskaton").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_eska, 11));
        marker = mMap.addMarker(new MarkerOptions().position(se_mir).title("SouthEast Bank\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_mir, 11));
        marker = mMap.addMarker(new MarkerOptions().position(se_dhk2).title("SouthEast Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_dhk2, 11));
        marker = mMap.addMarker(new MarkerOptions().position(se_ele).title("SouthEast Bank\nElephant Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_ele, 11));
        marker = mMap.addMarker(new MarkerOptions().position(se_rafi).title("SouthEast Bank\nBir Uttam Rafiqul Islam Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_rafi, 11));
        marker = mMap.addMarker(new MarkerOptions().position(se_bana).title("SouthEast Bank\nBanasree").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_bana, 11));
        marker = mMap.addMarker(new MarkerOptions().position(se_mog).title("SouthEast Bank\nMogbazar").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(se_mog, 11));

        marker = mMap.addMarker(new MarkerOptions().position(hk_dhk1).title("The HongKong and Shanghai Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hk_dhk1, 11));
        marker = mMap.addMarker(new MarkerOptions().position(hk_dhk2).title("The HongKong and Shanghai Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hk_dhk2, 11));
        marker = mMap.addMarker(new MarkerOptions().position(hk_begum).title("The HongKong and Shanghai Bank\nBegum Rokeya Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hk_begum, 11));
        //atm
        marker = mMap.addMarker(new MarkerOptions().position(hk_dhk3).title("The HongKong and Shanghai Bank ATM\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hk_dhk3, 11));
        marker = mMap.addMarker(new MarkerOptions().position(hk_gul).title("The HongKong and Shanghai Bank ATM\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hk_gul, 11));
        marker = mMap.addMarker(new MarkerOptions().position(hk_bir).title("The HongKong and Shanghai Bank ATM\nBir Uttam Mir Shakhawat Sarak").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hk_bir, 11));
        marker = mMap.addMarker(new MarkerOptions().position(hk_bir_uttam).title("The HongKong and Shanghai Bank ATM\nBir Uttam CR Datta Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hk_bir_uttam, 11));
        marker = mMap.addMarker(new MarkerOptions().position(hk_bashu).title("The HongKong and Shanghai Bank ATM\nBashundhara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hk_bashu, 11));
        marker = mMap.addMarker(new MarkerOptions().position(hk_water).title("The HongKong and Shanghai Bank ATM\nWater Works Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hk_water, 11));

        marker = mMap.addMarker(new MarkerOptions().position(st_gul).title("Standard Bank\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(st_gul, 11));
        marker = mMap.addMarker(new MarkerOptions().position(st_bir).title("Standard Bank\nBir Uttam AK Khandakar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(st_bir, 11));
        marker = mMap.addMarker(new MarkerOptions().position(st_pragati).title("Standard Bank\nPragati Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(st_pragati, 11));
        marker = mMap.addMarker(new MarkerOptions().position(st_moha).title("Standard Bank\nMohammadpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(st_moha, 11));
        marker = mMap.addMarker(new MarkerOptions().position(st_dhan).title("Standard Bank\nDhanmondi").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(st_dhan, 11));
        marker = mMap.addMarker(new MarkerOptions().position(st_lions).title("Standard Bank\nNorthern Lions").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(st_lions, 11));
        marker = mMap.addMarker(new MarkerOptions().position(st_dhk1).title("Standard Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(st_dhk1, 11));
        marker = mMap.addMarker(new MarkerOptions().position(st_cresent).title("Standard Bank\nTopkhana").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(st_cresent, 11));
        marker = mMap.addMarker(new MarkerOptions().position(st_moti).title("Standard Bank\nMotijheel").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(st_moti, 11));
        marker = mMap.addMarker(new MarkerOptions().position(st_air).title("Standard Bank\nAirport").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(st_air, 11));
        marker = mMap.addMarker(new MarkerOptions().position(st_shahi).title("Standard Bank\nShahi Bhaban").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(st_shahi, 11));
        marker = mMap.addMarker(new MarkerOptions().position(st_momen).title("Standard Bank\nMomenbug Chowrasta").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(st_momen, 11));
        //atm
        marker = mMap.addMarker(new MarkerOptions().position(st_zoo).title("Standard Bank ATM\nZoo Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(st_zoo, 11));

        marker = mMap.addMarker(new MarkerOptions().position(tr_bir).title("Trust Bank Limited\nBir Uttam AK Khandakar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_bir, 11));
        marker = mMap.addMarker(new MarkerOptions().position(tr_gul).title("Trust Bank Limited\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_gul, 11));
        marker = mMap.addMarker(new MarkerOptions().position(tr_kemal).title("Trust Bank Limited\nKemal Ataturk Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_kemal, 11));
        marker = mMap.addMarker(new MarkerOptions().position(tr_cant).title("Trust Bank Limited\nCantonment").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_cant, 11));
        marker = mMap.addMarker(new MarkerOptions().position(tr_north1).title("Trust Bank Limited\nNorth Kafrul").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_north1, 11));
        marker = mMap.addMarker(new MarkerOptions().position(tr_shaheed).title("Trust Bank Limited\nShaheed Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_shaheed, 11));
        marker = mMap.addMarker(new MarkerOptions().position(tr_trade).title("Trust Bank Limited\nTrade Center").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_trade, 11));
        marker = mMap.addMarker(new MarkerOptions().position(tr_pragati).title("Trust Bank Limited\nPragati Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_pragati, 11));
        marker = mMap.addMarker(new MarkerOptions().position(tr_kakrail).title("Trust Bank Limited\nKakrail").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_kakrail, 11));
        marker = mMap.addMarker(new MarkerOptions().position(tr_pilkhana).title("Trust Bank Limited\nPilkhana").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_pilkhana, 11));
        marker = mMap.addMarker(new MarkerOptions().position(tr_sena).title("Trust Bank Limited\nSena Kalyan Bahban").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_sena, 11));
        marker = mMap.addMarker(new MarkerOptions().position(tr_dil).title("Trust Bank Limited\nDilkhusha").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_dil, 11));
        marker = mMap.addMarker(new MarkerOptions().position(tr_moha).title("Trust Bank Limited\nMohammadpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_moha, 11));
        //atm
        marker = mMap.addMarker(new MarkerOptions().position(tr_canto).title("Trust Bank ATM\nCantonment").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_canto, 11));
        marker = mMap.addMarker(new MarkerOptions().position(tr_shahed).title("Trust Bank ATM\nShaheed Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_shahed, 11));
        marker = mMap.addMarker(new MarkerOptions().position(tr_saudi).title("Trust Bank ATM\nSaudi Colony").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_saudi, 11));
        marker = mMap.addMarker(new MarkerOptions().position(tr_falcon).title("Trust Bank ATM\nFalcon Hall Adjacent").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_falcon, 11));
        marker = mMap.addMarker(new MarkerOptions().position(tr_rajni).title("Trust Bank ATM\nRajnigandha").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_rajni, 11));
        marker = mMap.addMarker(new MarkerOptions().position(tr_south).title("Trust Bank ATM\nSouth Gate Naval HQ").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_south, 11));
        marker = mMap.addMarker(new MarkerOptions().position(tr_navy).title("Trust Bank ATM\nNavy Head Quarter").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_navy, 11));
        marker = mMap.addMarker(new MarkerOptions().position(tr_army).title("Trust Bank ATM\nArmy Head Quarter").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_army, 11));
        marker = mMap.addMarker(new MarkerOptions().position(tr_cmh).title("Trust Bank ATM\nCMH Hospital").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_cmh, 11));
        marker = mMap.addMarker(new MarkerOptions().position(tr_prese).title("Trust Bank ATM\nGono Bhaban").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_prese, 11));
        marker = mMap.addMarker(new MarkerOptions().position(tr_bhan).title("Trust Bank ATM\nBhasantek").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_bhan, 11));
        marker = mMap.addMarker(new MarkerOptions().position(tr_apollo).title("Trust Bank ATM\nApollo Hospital Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_apollo, 11));
        marker = mMap.addMarker(new MarkerOptions().position(tr_dhan).title("Trust Bank ATM\nDhanmondi").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_dhan, 11));
        marker = mMap.addMarker(new MarkerOptions().position(tr_moham).title("Trust Bank ATM\nMohammadpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_moham, 11));
        marker = mMap.addMarker(new MarkerOptions().position(tr_kak).title("Trust Bank ATM\nKakrail").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_kak, 11));
        marker = mMap.addMarker(new MarkerOptions().position(tr_sat).title("Trust Bank ATM\nSatmosjid Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_sat, 11));
        marker = mMap.addMarker(new MarkerOptions().position(tr_mir).title("Trust Bank ATM\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_mir, 11));
        marker = mMap.addMarker(new MarkerOptions().position(tr_north).title("Trust Bank ATM\nPilkhana").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_north, 11));
        marker = mMap.addMarker(new MarkerOptions().position(tr_moti).title("Trust Bank ATM\nMotijheel").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_moti, 11));
        marker = mMap.addMarker(new MarkerOptions().position(tr_polashi).title("Trust Bank ATM\nAzimpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_polashi, 11));
        marker = mMap.addMarker(new MarkerOptions().position(tr_new).title("Trust Bank ATM\nNew Market").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr_new, 11));

        marker = mMap.addMarker(new MarkerOptions().position(ut_bir).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_bir, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ut_kazi).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_kazi, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ut_shahid).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_shahid, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ut_green).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_green, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ut_begum).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_begum, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ut_outer).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_outer, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ut_selina).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_selina, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ut_pantha).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_pantha, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ut_ring).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_ring, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ut_lake).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_lake, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ut_bir_utta).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_bir_utta, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ut_hatkola).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_hatkola, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ut_tipu).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_tipu, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ut_lal).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_lal, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ut_nawabpur).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_nawabpur, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ut_begum_bazar).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_begum_bazar, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ut_vip).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_vip, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ut_bangla).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_bangla, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ut_new).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_new, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ut_kamla).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_kamla, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ut_johnson).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_johnson, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ut_azim).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_azim, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ut_mir).title("Uttara Bank Limited\n").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_mir, 11));
        //atm
        marker = mMap.addMarker(new MarkerOptions().position(ut_bir_uttam).title("Uttara Bank ATM\nBir Uttam Rafiqul Islam Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_bir_uttam, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ut_shanti).title("Uttara Bank ATM\nShanti Nagar").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_shanti, 11));
        marker = mMap.addMarker(new MarkerOptions().position(ut_dar).title("Uttara Bank ATM\nDar-Us-Salam Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ut_dar, 11));

        marker = mMap.addMarker(new MarkerOptions().position(i_gul).title("Islami Bank Bangladesh Limited\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_gul, 11));
        marker = mMap.addMarker(new MarkerOptions().position(i_bir).title("Islami Bank Bangladesh Limited\nBir Uttam Rafiqul Islam Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_bir, 11));
        marker = mMap.addMarker(new MarkerOptions().position(i_bir_uttam).title("Islami Bank Bangladesh Limited\nBir Uttam Mir Shawkat Sarak").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_bir_uttam, 11));
        marker = mMap.addMarker(new MarkerOptions().position(i_new).title("Islami Bank Bangladesh Limited\nNew Circular Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_new, 11));
        marker = mMap.addMarker(new MarkerOptions().position(i_new_esk).title("Islami Bank Bangladesh Limited\nNew Eskaton Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_new_esk, 11));
        marker = mMap.addMarker(new MarkerOptions().position(i_pragati).title("Islami Bank Bangladesh Limited\nPragati Sarani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_pragati, 11));
        marker = mMap.addMarker(new MarkerOptions().position(i_kachu).title("Islami Bank Bangladesh Limited\nKachukhet").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_kachu, 11));
        marker = mMap.addMarker(new MarkerOptions().position(i_begum).title("Islami Bank Bangladesh Limited\nBegum Rokeya Sarani Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_begum, 11));
        marker = mMap.addMarker(new MarkerOptions().position(i_idb).title("Islami Bank Bangladesh Limited\nIDB Bhaban").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_idb, 11));
        marker = mMap.addMarker(new MarkerOptions().position(i_kushal).title("Islami Bank Bangladesh Limited\nKushal Center").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_kushal, 11));
        marker = mMap.addMarker(new MarkerOptions().position(i_mazar).title("Islami Bank Bangladesh Limited\nMazar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_mazar, 11));
        //atm
        marker = mMap.addMarker(new MarkerOptions().position(i_gulshan).title("Islami Bank Bangladesh ATM\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_gulshan, 11));
        marker = mMap.addMarker(new MarkerOptions().position(i_bir_uttam_ave).title("Islami Bank Bangladesh ATM\nBir Uttam Rafiqul Islam Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_bir_uttam_ave, 11));
        marker = mMap.addMarker(new MarkerOptions().position(i_eskar).title("Islami Bank Bangladesh ATM\nNew Eskaton Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_eskar, 11));
        marker = mMap.addMarker(new MarkerOptions().position(i_kazi).title("Islami Bank Bangladesh ATM\nKazi Nazrul Islam Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_kazi, 11));
        marker = mMap.addMarker(new MarkerOptions().position(i_bashud).title("Islami Bank Bangladesh ATM\nBashundhara").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_bashud, 11));
        marker = mMap.addMarker(new MarkerOptions().position(i_manikdi).title("Islami Bank Bangladesh ATM\nManikdi Bazar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_manikdi, 11));
        marker = mMap.addMarker(new MarkerOptions().position(i_mir).title("Islami Bank Bangladesh ATM\nMirpur Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_mir, 11));
        marker = mMap.addMarker(new MarkerOptions().position(i_shahedd).title("Islami Bank Bangladesh ATM\nShaheed Tajuddin Ahmed Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_shahedd, 11));
        marker = mMap.addMarker(new MarkerOptions().position(i_mazar_road).title("Islami Bank Bangladesh ATM\nMazar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_mazar_road, 11));
        marker = mMap.addMarker(new MarkerOptions().position(i_dhk1).title("Islami Bank Bangladesh ATM\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(i_dhk1, 11));

        marker = mMap.addMarker(new MarkerOptions().position(j_dhk1).title("Jamuna Bank Limited\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_dhk1, 11));
        marker = mMap.addMarker(new MarkerOptions().position(j_midas).title("Jamuna Bank Limited\nMidas Center").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_midas, 11));
        marker = mMap.addMarker(new MarkerOptions().position(j_moti).title("Jamuna Bank Limited\nMotijheel").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_moti, 11));
        marker = mMap.addMarker(new MarkerOptions().position(j_dhk2).title("Jamuna Bank Limited\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_dhk2, 11));
        marker = mMap.addMarker(new MarkerOptions().position(j_topkhana).title("Jamuna Bank Limited\nTopkhana Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_topkhana, 11));
        marker = mMap.addMarker(new MarkerOptions().position(j_chameli).title("Jamuna Bank Limited\nChameli").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_chameli, 11));
        marker = mMap.addMarker(new MarkerOptions().position(j_khales).title("Jamuna Bank Limited\nKhales Mansion").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_khales, 11));
        marker = mMap.addMarker(new MarkerOptions().position(j_bangshal).title("Jamuna Bank Limited\nBangshal").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_bangshal, 11));
        marker = mMap.addMarker(new MarkerOptions().position(j_dar).title("Jamuna Bank Limited\nDar-Us-Salam Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_dar, 11));
        marker = mMap.addMarker(new MarkerOptions().position(j_begum).title("Jamuna Bank Limited\nBegum Rokeya Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_begum, 11));
        marker = mMap.addMarker(new MarkerOptions().position(j_ismail).title("Jamuna Bank Limited\nIsmail Mansion").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_ismail, 11));
        marker = mMap.addMarker(new MarkerOptions().position(j_hazi).title("Jamuna Bank Limited\nHazi Ahmed Plaza").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_hazi, 11));
        marker = mMap.addMarker(new MarkerOptions().position(j_sector).title("Jamuna Bank Limited\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_sector, 11));
        marker = mMap.addMarker(new MarkerOptions().position(j_airport).title("Jamuna Bank Limited\nAirport").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_airport, 11));
        //atm
        marker = mMap.addMarker(new MarkerOptions().position(j_sonar).title("Jamuna Bank ATM\nSonargaon").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_sonar, 11));
        marker = mMap.addMarker(new MarkerOptions().position(j_ground).title("Jamuna Bank ATM\nAli Bhaban").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_ground, 11));
        marker = mMap.addMarker(new MarkerOptions().position(j_dil).title("Jamuna Bank ATM\nDilkhusha Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_dil, 11));
        marker = mMap.addMarker(new MarkerOptions().position(j_dit).title("Jamuna Bank ATM\nDIT Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_dit, 11));
        marker = mMap.addMarker(new MarkerOptions().position(j_bir_ut).title("Jamuna Bank ATM\nBir Uttam Samsul Alam Sarak").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_bir_ut, 11));
        marker = mMap.addMarker(new MarkerOptions().position(j_ground_floor).title("Jamuna Bank ATM\nShahjahanpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_ground_floor, 11));
        marker = mMap.addMarker(new MarkerOptions().position(j_cir).title("Jamuna Bank ATM\nCirculer Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_cir, 11));
        marker = mMap.addMarker(new MarkerOptions().position(j_rankin).title("Jamuna Bank ATM\nRankin Street").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_rankin, 11));
        marker = mMap.addMarker(new MarkerOptions().position(j_dhk3).title("Jamuna Bank ATM\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_dhk3, 11));
        marker = mMap.addMarker(new MarkerOptions().position(j_mirp).title("Jamuna Bank ATM\nMirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_mirp, 11));
        marker = mMap.addMarker(new MarkerOptions().position(j_dar1).title("Jamuna Bank ATM\nDar-Us-Salam Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_dar1, 11));
        marker = mMap.addMarker(new MarkerOptions().position(j_milk).title("Jamuna Bank ATM\nMilk Vita Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_milk, 11));
        marker = mMap.addMarker(new MarkerOptions().position(j_s_kafrul).title("Jamuna Bank ATM\nKafrul").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_s_kafrul, 11));
        marker = mMap.addMarker(new MarkerOptions().position(j_mohak).title("Jamuna Bank ATM\nMohakhali").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_mohak, 11));
        marker = mMap.addMarker(new MarkerOptions().position(j_gul1).title("Jamuna Bank ATM\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_gul1, 11));
        marker = mMap.addMarker(new MarkerOptions().position(j_gausul).title("Jamuna Bank ATM\nGausul Azam Ave ").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_gausul, 11));
        marker = mMap.addMarker(new MarkerOptions().position(j_dhk_mym).title("Jamuna Bank ATM\nDhaka Mymensingh Highway").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_dhk_mym, 11));
        marker = mMap.addMarker(new MarkerOptions().position(j_sat).title("Jamuna Bank ATM\nSatmosjid Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_sat, 11));
        marker = mMap.addMarker(new MarkerOptions().position(j_atish).title("Jamuna Bank ATM\nAtish Deepenkar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(j_atish, 11));

        marker = mMap.addMarker(new MarkerOptions().position(na_samad).title("National Credit and Commerce Bank\nSamad Super Market").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_samad, 11));
        marker = mMap.addMarker(new MarkerOptions().position(na_shaheed).title("National Credit and Commerce Bank\nMogbazar").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_shaheed, 11));
        marker = mMap.addMarker(new MarkerOptions().position(na_shahid).title("National Credit and Commerce Bank\nTaslim Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_shahid, 11));
        marker = mMap.addMarker(new MarkerOptions().position(na_classic).title("National Credit and Commerce Bank\nClassic Center Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_classic, 11));
        marker = mMap.addMarker(new MarkerOptions().position(na_uttara).title("National Credit and Commerce Bank\nUttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_uttara, 11));
        marker = mMap.addMarker(new MarkerOptions().position(na_spectra).title("National Credit and Commerce Bank\nSpectra Maqsood Tower").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_spectra, 11));
        marker = mMap.addMarker(new MarkerOptions().position(na_quality).title("National Credit and Commerce Bank\nQuality Center").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_quality, 11));
        marker = mMap.addMarker(new MarkerOptions().position(na_nazrul).title("National Credit and Commerce Bank\nShahid Syed Nazrul Islam Sharani").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_nazrul, 11));
        marker = mMap.addMarker(new MarkerOptions().position(na_dhk1).title("National Credit and Commerce Bank\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_dhk1, 11));
        marker = mMap.addMarker(new MarkerOptions().position(na_toyenbee).title("National Credit and Commerce Bank\nToyenbee Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_toyenbee, 11));
        marker = mMap.addMarker(new MarkerOptions().position(na_fahad).title("National Credit and Commerce Bank\nFahad Complex").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_fahad, 11));
        marker = mMap.addMarker(new MarkerOptions().position(na_motij).title("National Credit and Commerce Bank\nMotijheel").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_motij, 11));
        marker = mMap.addMarker(new MarkerOptions().position(na_nawab).title("National Credit and Commerce Bank\nNawabpur Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_nawab, 11));
        marker = mMap.addMarker(new MarkerOptions().position(na_babu).title("National Credit and Commerce Bank\nBabu Bazar").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_babu, 11));
        marker = mMap.addMarker(new MarkerOptions().position(na_dhk_mawa).title("National Credit and Commerce Bank\nDhaka Mymensingh Highway").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_dhk_mawa, 11));
        marker = mMap.addMarker(new MarkerOptions().position(na_bashu).title("National Credit and Commerce Bank\nBashundhara").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_bashu, 11));
        marker = mMap.addMarker(new MarkerOptions().position(na_kc).title("National Credit and Commerce Bank\nKC Plaza").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_kc, 11));
        marker = mMap.addMarker(new MarkerOptions().position(na_bangshal).title("National Credit and Commerce Bank\nBangshal").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_bangshal, 11));
        marker = mMap.addMarker(new MarkerOptions().position(na_pantha).title("National Credit and Commerce Bank\nPanthapath").icon((BitmapDescriptorFactory.fromResource(R.drawable.bank_icons))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_pantha, 11));
        //atm
        marker = mMap.addMarker(new MarkerOptions().position(na_gulsh).title("National Credit and Commerce Bank ATM\nGulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_gulsh, 11));
        marker = mMap.addMarker(new MarkerOptions().position(na_bbtoa).title("National Credit and Commerce Bank ATM\nBBTOA Building").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_bbtoa, 11));
        marker = mMap.addMarker(new MarkerOptions().position(na_mirpu).title("National Credit and Commerce Bank ATM\nBBTOA BUilding").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_mirpu, 11));
        marker = mMap.addMarker(new MarkerOptions().position(na_ram).title("National Credit and Commerce Bank ATM\nRam Krishna Mission").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_ram, 11));
        marker = mMap.addMarker(new MarkerOptions().position(na_dhk2).title("National Credit and Commerce Bank ATM\nDhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_dhk2, 11));
        marker = mMap.addMarker(new MarkerOptions().position(na_school).title("National Credit and Commerce Bank ATM\nSchool Super Market").icon((BitmapDescriptorFactory.fromResource(R.drawable.atm_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(na_school, 11));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        timecounter = 0;
        // inflat and return the layout
        View v = inflater.inflate(R.layout.fragment_bank, container, false);
        spinner = (Spinner) v.findViewById(R.id.spinner);
        linearLayout2 = (LinearLayout) v.findViewById(R.id.linearLayoutSpinner);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView) v.findViewById(android.R.id.text1)).setText("");
                    ((TextView) v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }

                return v;
            }

            @Override
            public int getCount() {
                return super.getCount() - 1; // you dont display last item. It is used as hint.
            }

        };

        adapter1.add("Brac Bank");
        adapter1.add("City Bank");
        adapter1.add("United Commercial Bank");
        adapter1.add("Dutch Bangla Bank");
        adapter1.add("HSBC");
        adapter1.add("Standard Chartered Bank");
        adapter1.add("Prime Bank");
        adapter1.add("Eastern Bank");
        adapter1.add("AB Bank Limited");
        adapter1.add("Bank Asia");
        adapter1.add("Exim Bank");
        adapter1.add("Mercantile Bank");
        adapter1.add("Mutual Trust Bank");
        adapter1.add("The Premier Bank");
        adapter1.add("One Bank");
        adapter1.add("South East Bank");
        adapter1.add("The Hongkong and Shanghai Bank");
        adapter1.add("Standard Bank");
        adapter1.add("Trust Bank Limited");
        adapter1.add("Uttara Bank Limited");
        adapter1.add("Jamuna Bank Limited");
        adapter1.add("National Credit and Commerce Bank");
        adapter1.add("Show all");
        adapter1.add("Search By Name");

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter1);
        spinner.setSelection(adapter1.getCount()); //set the hint the default selection so it appears on launch.


        spinner2 = (Spinner) v.findViewById(R.id.spinner2);
        adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView) v.findViewById(android.R.id.text1)).setText("");
                    ((TextView) v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }

                return v;
            }

            @Override
            public int getCount() {
                return super.getCount() - 1; // you dont display last item. It is used as hint.
            }

        };

        adapter2.add("Branch");
        adapter2.add("ATM");
        adapter2.add("Search By Branch/ATM");

        adapter2.setDropDownViewResource(R.layout.trends_spinner_list);
        spinner2.setAdapter(adapter2);
        spinner2.setSelection(adapter2.getCount());

        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        Menu menu = null;
        setUpMapIfNeeded_1(v);
        processMap(v);
        Toast.makeText(mContext, "Welcome to the Bank/ATM Locator. Please Select Bank that you are looking for from above search area", Toast.LENGTH_LONG).show();
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }


    int searchCounter = 0;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //pDialog.setCancelable(false);

        switch (item.getItemId()) {
            case R.id.action_search:
                searchCounter++;
                if (searchCounter % 2 == 0) {
                    linearLayout2.setVisibility(View.GONE);
                    searchCounter = 0;
                } else {
                    linearLayout2.setVisibility(View.VISIBLE);
//                    spinner.setVisibility(View.VISIBLE);
//                    spinner2.setVisibility(View.VISIBLE);
                }
//                return true;
//            default:
//                break;

        }

        return false;

    }

    // Setting Up the Map after OnCreateView is called

    private void setUpMapIfNeeded_1(View inflatedView) {

        if (mMap == null) {
            mMap = ((MapView) inflatedView.findViewById(R.id.mapView)).getMap();
            mMap.setOnMyLocationChangeListener(myLocationChangeListener);
            if (mMap != null) {
                mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                    @Override
                    public View getInfoWindow(Marker marker) {
                        return null;
                    }

                    @Override
                    public View getInfoContents(Marker marker) {

                        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View v = inflater.inflate(R.layout.windowlayout4, null);

                        TextView areaName = (TextView) v.findViewById(R.id.AreaName);
                        areaName.setText(marker.getTitle());
                        return v;
                    }
                });
            }
//            mLocationClient = new GoogleApiClient.Builder(mContext)
//                    .addApi(Drive.API)
//                    .addScope(Drive.SCOPE_FILE)
//                    .addConnectionCallbacks(this)
//                    .addOnConnectionFailedListener(this)
//                    .build();

            if (mMap != null) {
                setUpMap_1();
            }
        }

    }

    // Initially Starting the Map and Everything
    private void setUpMap_1() {
        // Check if we were successful in obtaining the map.
        if (mMap != null) {
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
//            GetMyLocation();
            mMap.setOnCameraChangeListener(getCameraChangeListener());
            GoToLoadMethod();
        }
    }

    // What happens if the Map resumes - i.e : After a Phone Call
    @Override
    public void onResume() {
        timecounter = 0;
        super.onResume();
        mMapView.onResume();
        setUpMapIfNeeded_2();
    }

    // Setting up the Map when it resumes!!!
    private void setUpMapIfNeeded_2() {
        try {
//            mLocationClient = new GoogleApiClient.Builder(mContext)
//                    .addApi(Drive.API)
//                    .addScope(Drive.SCOPE_FILE)
//                    .addConnectionCallbacks(this)
//                    .addOnConnectionFailedListener(this)
//                    .build();
            if (mMap != null) {
                setUpMap_2();
            }
        } catch (Exception e) {
            Toast.makeText(mContext, "Please turn on your GPS and Internet and then try again!", Toast.LENGTH_LONG).show();
        }
    }

    // This is the Method where the Actual Setting up happens!
    private void setUpMap_2() {
        try {
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                GoToLoadMethod();
            }
        } catch (Exception e) {
            Toast.makeText(mContext, "Please turn on your GPS and Internet and then try again!", Toast.LENGTH_LONG).show();
        }
    }

    ImageView gpsView;

    // Calling the GPSListener and getting the Locate Me to Center
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        // get the button view
//        gpsView = (ImageView) getView().findViewById(R.id.gpslocationView);
//        // set a onclick listener for when the button gets clicked
//        gpsView.setOnClickListener(new View.OnClickListener() {
//            // Start new list activity
//            public void onClick(View v) {
//                gpsCounter = 0;
//                BankFragment.this.GetMyLocation();
//            }
//        });
    }

    // What happens if the Maps is Paused
    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    // What happens if the Map is Destroyed
    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    // What happens if the Map is running on Low Memory
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
    //------------------------------------------>Ignoring this section start here<-------------------------------------------

    static final LatLng DHAKA = new LatLng(23.727640, 90.410578);

    List<Marker> mMarkers = new ArrayList<Marker>();


    int gpsCounter = 0;

    // This Method is Called to Get my Current Location

//    private void GetMyLocation() {
//        mMap.setOnMyLocationChangeListener(myLocationChangeListener);
//    }


    LatLng oldLoc;

    // This Method is Called to Get My GPS Change - the myLocationChangeListener

    GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {

        @Override
        public void onMyLocationChange(Location location) {
            // iAmHereMarker.remove();

            if (gpsCounter == 0) {

                gpsCounter = 1;

                LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());

                oldLoc = loc;


                if (mMap != null) {
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 16.0f));
                }
            }

        }
    };

    //-------------------------------------------->Stop<---------------------------------------------------
    // This Method Waits 3 Seconds so that Camera Can Load and then Loads all the PushPins where they belong

    private void GoToLoadMethod() {
        Handler mHandler = new Handler();

        mHandler.postDelayed(new Runnable() {
            public void run() {
            }
        }, 3000);

    }

    GoogleApiClient mLocationClient;

    // What Happens if the Map is Connected to the Location Service

    @Override
    public void onConnected(Bundle bundle) {
        Toast.makeText(mContext, "Connected to Location Service", Toast.LENGTH_SHORT).show();
    }


    // What happens if the Connection to Location Service is Suspended for some Reason

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(mContext, "Connection to Location Service Disrupted", Toast.LENGTH_SHORT).show();
    }


    // What happens if No Connection to the Location Service is available

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(mContext, "Can not connect to Location Service", Toast.LENGTH_SHORT).show();
    }


    int timecounter = 0;

    // What happens if the Zoom Level of the Map is Changed through Listener

    private float currentZoom = -1;


    public GoogleMap.OnCameraChangeListener getCameraChangeListener() {
        return new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition position) {
                if (currentZoom != position.zoom) {
                    {
                        currentZoom = position.zoom;

                        if (timecounter == 1) {

                            if (currentZoom < 12) {
                                for (int i = 0; i < mMarkers.size(); i++) {
                                    mMarkers.get(i).setVisible(false);
                                }
                            } else {
                                for (int i = 0; i < mMarkers.size(); i++) {
                                    mMarkers.get(i).setVisible(true);
                                }
                            }
                        }


                    }

                }
            }

        };
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mySearchView.clearFocus();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
