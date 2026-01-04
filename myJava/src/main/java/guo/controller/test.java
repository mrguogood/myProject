package guo.controller;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

@Service
public class test {
    // 线程池配置
    private static final int CORE_POOL_SIZE = 4;
    private static final int MAX_POOL_SIZE = 8;
    private static final long KEEP_ALIVE_TIME = 60L;
    private static final BlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingQueue<>(100);

    // 定时器配置 (单位: 毫秒)

    private static final long INITIAL_DELAY = 5000;    // 首次执行延迟5秒
    private static final long LOG_CLEAN_INTERVAL = 60 * 60 * 1000;  // 每小时清理日志
    private static final long SYNC_INTERVAL = 30 * 60 * 1000;       // 每30分钟同步数据

    // 创建线程池
    private static final ExecutorService executor = new ThreadPoolExecutor(
            CORE_POOL_SIZE,
            MAX_POOL_SIZE,
            KEEP_ALIVE_TIME,
            TimeUnit.SECONDS,
            WORK_QUEUE,
            new ThreadPoolExecutor.CallerRunsPolicy()
    );

    public static void main(String[] args) {
        System.out.println("Starting scheduled cleanup system...");

        // 创建定时任务调度器
        Timer timer = new Timer("ScheduledCleanupTimer", true);

        // 定时日志清理任务
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("[" + new Date() + "] Triggering log cleanup task...");
                executor.submit(new LogCleanupTask());
            }
        }, INITIAL_DELAY, LOG_CLEAN_INTERVAL);

        // 定时Redis-DB同步任务
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("[" + new Date() + "] Triggering Redis-DB sync task...");
                executor.submit(new RedisDBSyncTask());
            }
        }, INITIAL_DELAY + 10_000, SYNC_INTERVAL);

        // 添加关闭钩子
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down scheduled tasks...");
            timer.cancel();
            executor.shutdown();
            try {
                if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
            }
        }));
    }

    // 日志清理任务
    static class LogCleanupTask implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("[" + Thread.currentThread().getName() + "] Starting log cleanup...");

                // 实际日志清理逻辑
                cleanOldLogFiles();

                System.out.println("[" + Thread.currentThread().getName() + "] Log cleanup completed!");
            } catch (Exception e) {
                System.err.println("Log cleanup failed: " + e.getMessage());
            }
        }

        private void cleanOldLogFiles() {
            // 伪代码: 实际清理逻辑
            // 1. 扫描日志目录
            // 2. 删除超过30天的日志文件
            // 3. 压缩归档重要日志
            // 示例: FileUtils.deleteOldFiles(logDir, 30)
            try {
                Thread.sleep(2000); // 模拟耗时操作
            } catch (InterruptedException ignored) {}
        }
    }

    // Redis与数据库同步任务
    static class RedisDBSyncTask implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("[" + Thread.currentThread().getName() + "] Starting Redis-DB sync...");

                // 实际同步逻辑
                syncRedisWithDatabase();

                System.out.println("[" + Thread.currentThread().getName() + "] Redis-DB sync completed!");
            } catch (Exception e) {
                System.err.println("Redis-DB sync failed: " + e.getMessage());
            }
        }

        private void syncRedisWithDatabase() {
            // 伪代码: 实际同步逻辑
            // 1. 从数据库读取需要同步的数据
            // 2. 更新Redis缓存
            // 3. 处理数据一致性校验
            // 示例: redisTemplate.opsForValue().set(key, dbData)
            try {
                Thread.sleep(3000); // 模拟耗时操作
            } catch (InterruptedException ignored) {}
        }
    }
}
