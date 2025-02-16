package backend;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class Main implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        ReadExcel read_excel = new ReadExcel();
        read_excel.write_file("C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\maker_file.xlsx", "maker");
        read_excel.write_file("C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\checker_file.xlsx", "checker");
        return RepeatStatus.FINISHED;
    }
}


