import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SpiralExpressTestModule } from '../../../test.module';
import { ColiUpdateComponent } from 'app/entities/coli/coli-update.component';
import { ColiService } from 'app/entities/coli/coli.service';
import { Coli } from 'app/shared/model/coli.model';

describe('Component Tests', () => {
  describe('Coli Management Update Component', () => {
    let comp: ColiUpdateComponent;
    let fixture: ComponentFixture<ColiUpdateComponent>;
    let service: ColiService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SpiralExpressTestModule],
        declarations: [ColiUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ColiUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ColiUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ColiService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Coli(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Coli();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
