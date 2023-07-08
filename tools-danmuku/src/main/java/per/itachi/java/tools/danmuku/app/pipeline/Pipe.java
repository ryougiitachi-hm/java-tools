package per.itachi.java.tools.danmuku.app.pipeline;

import per.itachi.java.tools.danmuku.app.port.Strategyable;

public interface Pipe extends Strategyable {

    String PIPE_CONVERT_TENCENT_TO_BILIBILI_DANMAKU = "convert-tencent-to-bilibili-danmaku";

    String PIPE_CONVERT_IQIYI_TO_BILIBILI_DANMAKU = "convert-iqiyi-to-bilibili-danmaku";

    String PIPE_CONVERT_BILIBILI_TO_ASS_DANMAKU = "convert-bilibili-to-ass-danmaku";

    /**
     * @return only when return value is true does next pipe perform.
     * */
    boolean perform(PipelineContext context);
}
