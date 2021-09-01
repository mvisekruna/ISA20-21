import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MonthlyReport } from 'src/app/model/monthly-report';
import { MonthlyReportServiceService } from 'src/app/service/monthly-report-service.service';

@Component({
  selector: 'app-monthly-report',
  templateUrl: './monthly-report.component.html',
  styleUrls: ['./monthly-report.component.css']
})
export class MonthlyReportComponent implements OnInit {

  monthlyReport: MonthlyReport
  constructor(private monthlyReportService: MonthlyReportServiceService, private router: Router) {
    this.monthlyReport = new MonthlyReport;
   }

  ngOnInit(): void {

    this.monthlyReportService.countExaminations().subscribe(data => {
      console.log(data);
      this.monthlyReport = data;
    })
  }

}
