
    项目初衷
        
        将小视频 转化为gif图
        
    拓展
    
        拍摄小视频 转化为gif图
        
        
        git procedure
        
            git init 
            git remote add origin -resource link-
            git add .
            git commit -m"log"
            git push --set-upstream origin master
        
        over
        
    support
    
        [split frame]https://github.com/wseemann/FFmpegMediaMetadataRetriever
        [open album]https://github.com/yanzhenjie/Album
       
    quote
    
        获取一帧的关键代码为：
        Bitmap bitmap = mediaMetadataRetriever.getFrameAtTime(timeMs * 1000, MediaMetadataRetriever.OPTION_CLOSEST);
        public Bitmap getFrameAtTime(long timeUs, int option) 
        第一个参数是传入时间，只能是us(微秒)
        第二个参数:
        OPTION_CLOSEST    在给定的时间，检索最近一个帧,这个帧不一定是关键帧。
        OPTION_CLOSEST_SYNC   在给定的时间，检索最近一个同步与数据源相关联的的帧（关键帧）。
        OPTION_NEXT_SYNC 在给定时间之后检索一个同步与数据源相关联的关键帧。
        OPTION_PREVIOUS_SYNC  顾名思义，同上
        这里为了提取我们想要的帧，不使用关键帧，所以用 OPTION_CLOSEST .
    
        最终的测试结果并不理想，连续取20帧画面，其中真正有效的只有7张，其余都是重复的，
        原因为即使是使用参数OPTION_CLOSEST，程序仍然会去取指定时间临近的关键帧，如10s-15s总是取同一帧，因此这种方法不可用。