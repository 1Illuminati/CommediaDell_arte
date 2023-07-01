package org.red.library.util;

import org.bukkit.Bukkit;
import org.red.library.CommediaDell_arte;

public class Scheduler {
    private Scheduler() {
        throw new IllegalStateException("Utility class");
    }

    //일정시간뒤에 작동
    public static void delayScheduler(final RunnableEx task, int startDelayTick) {
        task.setTaskId(Bukkit.getScheduler().scheduleSyncDelayedTask(CommediaDell_arte.getPlugin(), task, startDelayTick));
    }

    //일정시간마다 반복
    public static void repeatDelayScheduler(final RunnableEx task, int delayTick, int repeat) {
        repeatDelayScheduler(task, 0, delayTick, repeat);
    }

    //일정시간뒤에 일정시간마다 반복
    public static void repeatDelayScheduler(final RunnableEx task, int startDelayTick, int delayTick, int repeat) {
        task.setRepeat(repeat);
        task.setTaskId(Bukkit.getScheduler().scheduleSyncRepeatingTask(CommediaDell_arte.getPlugin(), task, startDelayTick, delayTick));
    }

    //영구적인 무한반복 가급적 사용을하지 말것
    public static void infiniteRepeatScheduler(final RunnableEx task, int startDelayTick, int delayTick) {
        task.setTaskId(Bukkit.getScheduler().scheduleSyncRepeatingTask(CommediaDell_arte.getPlugin(), task, startDelayTick, delayTick));
    }

    public abstract static class RunnableEx implements Runnable {
        private int taskId;
        private int repeat = Integer.MAX_VALUE;
        private int count;

        public int getTaskId() {
            return taskId;
        }

        public void setTaskId(int taskId) {
            this.taskId = taskId;
        }

        public int getRepeat() {
            return repeat;
        }

        public void setRepeat(int repeat) {
            this.repeat = repeat;
        }

        public int getCount() {
            return this.count;
        }

        @Override
        public void run() {
            if (this.repeat <= this.count) {
                stop();
            }

            function();
            this.count++;
        }

        public abstract void function();

        public void stop() {
            Bukkit.getScheduler().cancelTask(this.taskId);
        }
    }
}
