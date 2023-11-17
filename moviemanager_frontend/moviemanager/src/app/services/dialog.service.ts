import { Injectable } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { YesnoDialogComponent } from '../yesno.dialog/yesno.dialog.component';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DialogService {
  constructor(private dialog: MatDialog) { }

  openDialog(): Observable<any> {
    const dialogRef: MatDialogRef<YesnoDialogComponent> = this.dialog.open(YesnoDialogComponent, {
      width: '300px',
      // Dodaj dodatkowe opcje dialogu
    });
    return dialogRef.afterClosed();
  }

}
