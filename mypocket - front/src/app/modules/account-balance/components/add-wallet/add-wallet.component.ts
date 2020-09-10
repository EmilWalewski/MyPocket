import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Wallet, WalletService } from 'src/app/services/wallet/wallet.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-add-wallet',
  templateUrl: './add-wallet.component.html',
  styleUrls: ['./add-wallet.component.scss']
})
export class AddWalletComponent implements OnInit {

  walletFormGroup: FormGroup;

  constructor(private formBuilder: FormBuilder, private walletService: WalletService,
    private router: Router, private route: ActivatedRoute) {
    this.walletFormGroup = this.createAddWalletForm(formBuilder);
  }

  ngOnInit(): void {
  }

  createAddWalletForm(builder: FormBuilder) {
    return builder.group({
      name: '',
      quantity: ''
    });
  }

  onSubmit() {
    const result: Wallet = Object.assign({}, this.walletFormGroup.value);
    let errors = '';
    this.walletService.uploadWallet(result)
      .subscribe(data => {
        this.router.navigate(['../'], { relativeTo: this.route });
      },
        error => {
          console.log('errors occured');
          error.errorsList.forEach(element => {
            errors += element + '\n';
          });

          alert(errors);
        }
      );
  }


}
