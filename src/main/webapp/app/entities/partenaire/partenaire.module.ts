import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SpiralExpressSharedModule } from 'app/shared/shared.module';
import { PartenaireComponent } from './partenaire.component';
import { PartenaireDetailComponent } from './partenaire-detail.component';
import { PartenaireUpdateComponent } from './partenaire-update.component';
import { PartenaireDeleteDialogComponent } from './partenaire-delete-dialog.component';
import { partenaireRoute } from './partenaire.route';

@NgModule({
  imports: [SpiralExpressSharedModule, RouterModule.forChild(partenaireRoute)],
  declarations: [PartenaireComponent, PartenaireDetailComponent, PartenaireUpdateComponent, PartenaireDeleteDialogComponent],
  entryComponents: [PartenaireDeleteDialogComponent],
})
export class SpiralExpressPartenaireModule {}
