import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-yesno.dialog',
  templateUrl: './yesno.dialog.component.html',
  styleUrl: './yesno.dialog.component.scss'
})
export class YesnoDialogComponent {
  constructor(public dialogRef: MatDialogRef<YesnoDialogComponent>) { }

  closeDialog(result: any): void {
    this.dialogRef.close(result);
  }
}
