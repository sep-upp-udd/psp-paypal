INSERT INTO public.merchant(
	id, client_id, disbursement_mode, success_address, error_address, uuid, name, platform_fee_currency,  platform_fee_value, shoppers) VALUES
	(nextval('merchant_sequence'),'AU-xyHyWRWXNDFDSTqWb6qnWfkczBnNwACSexYuwFGXobrTT7uqSjv6Xm1Do_xH7VU3FBi91DB4_JDFR',0,'http://localhost:8086/payment/success', 'http://localhost:8086/payment/error',
	'1','Web shop',0, 0,false);