import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { DataStorageService } from './data-storage.service';
import { MatDialog } from '@angular/material';
import { AppConfigService } from 'src/app/app-config.service';
import { TranslateService } from '@ngx-translate/core';
import { DialogComponent } from 'src/app/shared/dialog/dialog.component';
import { RequestModel } from '../models/request.model';
import * as appConstants from '../../app.constants';
import { CenterModel } from '../models/center.model';

@Injectable({
  providedIn: 'root'
})

export class CommonService {

  actionMessages: any;

  constructor(
    private router: Router,
    private dataService: DataStorageService,
    private dialog: MatDialog,
    private appService: AppConfigService,
    private translate: TranslateService
  ) {
      translate.getTranslation(appService.getConfig().primaryLangCode).subscribe(result => {
        this.actionMessages = result.actionMessages;
      });
  }

  private showMessage(data: any) {
    this.dialog.open(DialogComponent, {
      width: '350px',
      data: {
        case: 'MESSAGE',
        ...data
      }
    });
  }

  private createMessage(type: string, listItem: string) {
    let obj = {};
    if (type === 'success') {
      obj = {
        title: this.actionMessages[listItem]['success-title'],
        message: this.actionMessages[listItem]['success-message'],
        btnTxt: this.actionMessages[listItem]['btnTxt']
      };
    } else if (type === 'error') {
      obj = {
        title: this.actionMessages[listItem]['error-title'],
        message: this.actionMessages[listItem]['error-message'],
        btnTxt: this.actionMessages[listItem]['btnTxt']
      };
    }
    this.showMessage(obj);
  }

  private updateCenter(callingFunction: string, data: CenterModel) {
    const request = new RequestModel(
      appConstants.registrationCenterCreateId,
      null,
      data
    );
    this.dataService.updateCenter(request).subscribe(response => {
      if (!response.errors || response.errors.length === 0) {
        this.createMessage('success', callingFunction);
        this.router.navigateByUrl(this.router.url);
      } else {
        this.createMessage('error', callingFunction);
      }
    }, error => this.createMessage('error', callingFunction));
  }

  private mapDataToObject(data: any): CenterModel {
    const primaryObject = new CenterModel(
      data.addressLine1,
      data.addressLine2,
      data.addressLine3,
      data.centerEndTime,
      data.centerStartTime,
      data.centerTypeCode,
      data.contactPerson,
      data.contactPhone,
      data.holidayLocationCode,
      this.appService.getConfig().primaryLangCode,
      data.latitude,
      data.postalCode,
      data.longitude,
      data.lunchEndTime,
      data.lunchStartTime,
      data.name,
      data.perKioskProcessTime,
      data.timeZone,
      data.workingHours,
      data.zoneCode,
      data.id,
      data.isActive,
      data.numberOfKiosks
    );
    return primaryObject;
  }

  centerView(data: any, url: string, idKey: string) {
    url = url.replace('$id', data[idKey]);
    this.router.navigateByUrl(url);
  }

  decommissionCenter(data: any, url: string, idKey: string) {
    this.dataService
      .decommissionCenter(data[idKey])
      .subscribe(response => {
        if (!response['errors']) {
          this.createMessage('success', 'decommission');
          if (this.router.url.indexOf('single-view') >= 0) {
            this.router.navigateByUrl('admin/resources/centers/view');
          } else {
            this.router.navigateByUrl(this.router.url);
          }
        } else {
          this.createMessage('error', 'decommission');
        }
      }, error => {
        this.createMessage('error', 'decommission');
      });
  }

  activateCenter(data: any, url: string, idKey: string) {
    const centerObject = this.mapDataToObject(data);
    centerObject.isActive = true;
    console.log(centerObject);
    this.updateCenter('activate', centerObject);
  }

  deactivateCenter(data: any, url: string, idKey: string) {
    const centerObject = this.mapDataToObject(data);
    centerObject.isActive = false;
    console.log(centerObject);
    this.updateCenter('activate', centerObject);
  }
}
