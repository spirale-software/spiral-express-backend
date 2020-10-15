import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPartenaire, Partenaire } from 'app/shared/model/partenaire.model';
import { PartenaireService } from './partenaire.service';
import { PartenaireComponent } from './partenaire.component';
import { PartenaireDetailComponent } from './partenaire-detail.component';
import { PartenaireUpdateComponent } from './partenaire-update.component';

@Injectable({ providedIn: 'root' })
export class PartenaireResolve implements Resolve<IPartenaire> {
  constructor(private service: PartenaireService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPartenaire> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((partenaire: HttpResponse<Partenaire>) => {
          if (partenaire.body) {
            return of(partenaire.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Partenaire());
  }
}

export const partenaireRoute: Routes = [
  {
    path: '',
    component: PartenaireComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'spiralExpressApp.partenaire.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PartenaireDetailComponent,
    resolve: {
      partenaire: PartenaireResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'spiralExpressApp.partenaire.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PartenaireUpdateComponent,
    resolve: {
      partenaire: PartenaireResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'spiralExpressApp.partenaire.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PartenaireUpdateComponent,
    resolve: {
      partenaire: PartenaireResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'spiralExpressApp.partenaire.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
