package backend;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;

public class ScheduledJobLauncher {

    private JobLauncher jobLauncher;
    private Job job;

    public void run() {
        try {
            JobParameters params = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
            JobExecution execution = jobLauncher.run(job, params);
            System.out.println("Job Execution Status: " + execution.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setJobLauncher(JobLauncher jobLauncher) {
        this.jobLauncher = jobLauncher;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}

