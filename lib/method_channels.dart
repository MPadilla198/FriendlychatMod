import 'dart:async';

import 'package:flutter/services.dart';



class MethodChannels {
  ////
  // For checking network connectivity of device
  ////
  static const MethodChannel _connectivityChannel = const MethodChannel('connectivity');

  static Future<bool> get isConnected => _connectivityChannel.invokeMethod('isConnected');
  static Future<bool> get isBluetooth => _connectivityChannel.invokeMethod('isBluetooth');
  static Future<bool> get isMobile => _connectivityChannel.invokeMethod('isMobile');
  static Future<bool> get isVPN => _connectivityChannel.invokeMethod('isVPN');
  static Future<bool> get isWifi => _connectivityChannel.invokeMethod('isWifi');
}