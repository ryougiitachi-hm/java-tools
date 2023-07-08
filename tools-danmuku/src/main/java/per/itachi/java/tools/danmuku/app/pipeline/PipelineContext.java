package per.itachi.java.tools.danmuku.app.pipeline;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PipelineContext {

    public static final String CONTEXT_PARAM_IQIYI_DANMAKU_FILE_PATH = "IQIYI_DANMAKU_FILE_PATH";

    public static final String CONTEXT_PARAM_TENCENT_DANMAKU_FILE_PATH = "TENCENT_DANMAKU_FILE_PATH";

    public static final String CONTEXT_PARAM_BILIBILI_DANMAKU_FILE_PATH = "BILIBILI_DANMAKU_FILE_PATH";

    private Map<String, String> params;

    public PipelineContext() {
        init();
    }

    private void init() {
        this.params = new HashMap<>();
    }

    public String getContextParam(String paramName) {
        return params.get(paramName);
    }

    public void putContextParam(String paramName, String paramValue) {
        params.put(paramName, paramValue);
    }

    public Set<String> getContextParamNames() {
        return params.keySet();
    }
}
