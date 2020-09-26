import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SpiralExpressTestModule } from '../../../test.module';
import { ColiDetailComponent } from 'app/entities/coli/coli-detail.component';
import { Coli } from 'app/shared/model/coli.model';

describe('Component Tests', () => {
  describe('Coli Management Detail Component', () => {
    let comp: ColiDetailComponent;
    let fixture: ComponentFixture<ColiDetailComponent>;
    const route = ({ data: of({ coli: new Coli(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SpiralExpressTestModule],
        declarations: [ColiDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ColiDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ColiDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load coli on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.coli).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
