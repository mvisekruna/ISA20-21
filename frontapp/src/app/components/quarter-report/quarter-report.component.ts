import { Component, OnInit } from '@angular/core';
import { QuarterReport } from 'src/app/model/quarter-report';
import { QuarterReportServiceService } from 'src/app/service/quarter-report-service.service';

@Component({
  selector: 'app-quarter-report',
  templateUrl: './quarter-report.component.html',
  styleUrls: ['./quarter-report.component.css']
})
export class QuarterReportComponent implements OnInit {

  quarterReport: QuarterReport;
  constructor(private quarterReportService: QuarterReportServiceService) { 
    this.quarterReport = new QuarterReport;
  }

  ngOnInit(): void {

    this.quarterReportService.countExaminations().subscribe(data => {
      console.log(data);
      this.quarterReport = data;
    });

  }

}
