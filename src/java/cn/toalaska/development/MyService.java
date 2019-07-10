package cn.toalaska.development;

import cn.toalaska.development.ui.Param;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@Data

@State(
        name = "FormatSetting",
        storages = {@Storage(
                file = "$APP_CONFIG$/development-helper.xml"
        )})


public class MyService implements PersistentStateComponent<List<Param>> {


    List<Param> params=new ArrayList<>();
    public static MyService getInstance() {
        return  ServiceManager.getService(MyService.class);
    }

    @Nullable
    @Override
    public List<Param> getState() {
        return params;
    }

    @Override
    public void loadState(@NotNull List<Param> params) {
        XmlSerializerUtil.copyBean(params, this);
    }

    @Override
    public void noStateLoaded() {

    }
}
